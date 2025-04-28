Steps to Connect:  
 
In Server Machine: 
ssh administrator@10.10.12.248 
 
sudo ufw allow 1027 
 
mkdir -p /home/administrator/myrmi 
cd /home/administrator/myrmi 
 
 
rmiregistry 1027 & 
java Server 
 
 
 
In Client Machine:  
 
scp Server.java PowerServerIntf.java PowerServerImpl.java administrator@10.10.12.248:/home/administrator/myrmi/ 
rm *.java 
 
scp Server.java PowerServerIntf.java PowerServerImpl.java administrator@10.10.12.248:/home/administrator/myrmi/ 
 
 
java Client