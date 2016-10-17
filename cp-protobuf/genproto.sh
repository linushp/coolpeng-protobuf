#!/bin/sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 [proto file] "
  exit 1
fi

#protoc --java_out=src/main/java/  --proto_path=./ $1
/usr/local/Cellar/protobuf/3.1.0/bin/protoc --java_out=src/main/java/  --proto_path=./ $1
echo "ok"







#https://github.com/grpc/grpc-java
#https://github.com/google/protobuf
#http://blog.csdn.net/lyjshen/article/details/52238234