blueprints-ramcloud-graph
=========================

A TinkerPop Blueprints implementation on top of RAMCloud

Setup
=====
 - Copy src/main/java/edu/stanford/ramcloud/JRamCloud.java and
src/main/cpp/edu_stanford_ramcloud_JRamCloud.cc to your
ramcloud/bindinds/java/edu/stanford/ramcloud directory, overwriting what is
already there.

 - Generate the C++ header files containing the function signatures for all the
native methods in the Java RamCloud library:

javah -cp ../../../ edu.stanford.ramcloud.JRamCloud

 - Compile the ramcloud C++ library (assuming ramcloud is in your ${HOME}
directory and you have already compiled ramcloud):

c++ -Wall -O3 -shared -fPIC -std=c++0x \
  -I/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.9.x86_64/include/ \
  -I/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.9.x86_64/include/linux/ \
  -I${HOME}/ramcloud/src/ -I${HOME}/ramcloud/obj.master/ \
  -I${HOME}/ramcloud/logcabin/ -I${HOME}/ramcloud/gtest/include/ \
  -L${HOME}/ramcloud/obj.master -o libedu_stanford_ramcloud_JRamCloud.so \
  edu_stanford_ramcloud_JRamCloud.cc -lramcloud

 - Update LD_LIBRARY_PATH (assuming ramcloud is in your ${HOME} directory) to
include the library and also any other ramcloud libraries:

export
LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${HOME}/ramcloud/bindings/java/edu/stanford/ramcloud:${HOME}/ramcloud/obj.master

 - Startup a ramcloud cluster somewhere and modify RamCloudGraph.java to point
to the coordinator.

 - Compile this package (blueprints-ramcloud-graph) using maven and run :)

Using Rexster
=============
 - git clone the rexster repository:

git clone https://github.com/tinkerpop/rexster.git

 - Compile rexster

mvn compile

 - Compile packaged rexster server

cd rexster-server/
mvn package

 - Copy blueprints-ramcloud-graph jar file into the compiled rexster-server
   library directory (use mvn package to build this jar file):

cp blueprints-ramcloud-graph-2.0.0.jar
~/git/rexster/rexster-server/target/rexster-server-2.5.0-SNAPSHOT-standalone/lib

 - Edit rexster-server config file

vim config/rexster.xml

 - Change web-root to be your public web-root directory

 - Add a ramcloud graph to the set of graphs to load up:

        <graph>
            <graph-enabled>true</graph-enabled>
            <graph-name>ramcloudgraph</graph-name>
            <graph-type>com.tinkerpop.rexster.config.RamCloudGraphConfiguration</graph-type>
        </graph>

 - Startup a ramcloud cluster

 - Startup the rexster server:

./bin/rexster.sh -start &

 - Done!
