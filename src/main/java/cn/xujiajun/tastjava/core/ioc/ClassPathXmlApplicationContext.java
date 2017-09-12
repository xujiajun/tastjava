package cn.xujiajun.tastjava.core.ioc;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beans = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext() throws Exception {
        SAXBuilder builder = new SAXBuilder();

        String basedir = System.getProperty("user.dir");

        File xmlFile = new File(basedir + "/src/main/resources/beans.xml");
        // 构造文档对象
        Document document = builder.build(xmlFile);
        // 获取根元素
        Element root = document.getRootElement();
        // 取到根元素所有元素
        List list = root.getChildren();

        setBeans(list);
    }

    /**
     * set Beans
     *
     * @param list
     * @throws Exception
     */
    private void setBeans(List list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);

            String id = element.getAttributeValue("id");

            String className = element.getAttributeValue("class");

            Object o = Class.forName(className).newInstance();

            beans.put(id, o);

            setProperty(element, o);
        }
    }

    /**
     * setProperty inject
     *
     * @param element
     * @param o
     * @throws Exception
     */
    private void setProperty(Element element, Object o) throws Exception {
        for (Element property : (List<Element>) element.getChildren("property")) {
            String name = property.getAttributeValue("name");
            String bean = property.getAttributeValue("bean");
            //从beans.xml中根据id取到类的对象
            Object beanObj = this.getBean(bean);
//            System.out.println(beanObj);//com.tastjava.dao.impl.UserDAOImpl@eed1f14
            String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            // 反射机制对方法进行调用，将对象在加载bean时就注入到环境上下文中
            Method m = o.getClass().getMethod(methodName, beanObj.getClass().getInterfaces()[0]);
            m.invoke(o, beanObj);
        }
    }

    @Override
    public Object getBean(String name) {
        return beans.get(name);
    }
}

