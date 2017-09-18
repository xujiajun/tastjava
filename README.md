# TastJava
The RESTful Web API framework for Java

## Performance Test

### Test environment

```
Server version: Apache Tomcat/9.0.0.M26
Server number:  9.0.0.0
OS Name:        Mac OS X
OS Version:     10.12.6
Architecture:   x86_64
CPU：Intel Core i7  1.7 GHz *2
RAM：8 GB
JVM Version:    1.8.0_144-b01
```

Test Result (for reference only):
```
➜  tastjava git:(develop) ✗ ab -n 5000 -c 20  http://localhost:8080/tastjava/user/1
This is ApacheBench, Version 2.3 <$Revision: 1757674 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 500 requests
Completed 1000 requests
Completed 1500 requests
Completed 2000 requests
Completed 2500 requests
Completed 3000 requests
Completed 3500 requests
Completed 4000 requests
Completed 4500 requests
Completed 5000 requests
Finished 5000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /tastjava/user/1
Document Length:        0 bytes

Concurrency Level:      20
Time taken for tests:   1.504 seconds
Complete requests:      5000
Failed requests:        0
Total transferred:      365000 bytes
HTML transferred:       0 bytes
Requests per second:    3323.71 [#/sec] (mean)
Time per request:       6.017 [ms] (mean)
Time per request:       0.301 [ms] (mean, across all concurrent requests)
Transfer rate:          236.94 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   1.4      0      57
Processing:     1    6   5.0      5      67
Waiting:        1    5   4.9      5      65
Total:          1    6   5.2      5      68

Percentage of the requests served within a certain time (ms)
  50%      5
  66%      7
  75%      8
  80%      8
  90%     10
  95%     11
  98%     14
  99%     25
 100%     68 (longest request)
 ```




