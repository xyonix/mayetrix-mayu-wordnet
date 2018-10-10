mvn deploy:deploy-file \
   -DrepositoryId=xyonix-release \
   -Durl=s3://com.xyonix.repo/release \
   -Dfile=ritaWN.jar \
   -DgroupId=com.xyonix.thirdparty \
   -DartifactId=ritaWN \
   -Dversion=1.0.0

 mvn deploy:deploy-file \
    -DrepositoryId=xyonix-release \
    -Durl=s3://com.xyonix.repo/release \
    -Dfile=supportWN.jar \
    -DgroupId=com.xyonix.thirdparty \
    -DartifactId=supportWN \
    -Dversion=1.0.0
