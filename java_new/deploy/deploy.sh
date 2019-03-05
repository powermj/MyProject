TIME=$(date +%Y-%m-%d-%H-%M-%S)
BACKUPDIR=/data/backup/msjava
BACKUPFILENAME=yqms_$TIME.war
#BACKUPFILENAME=ROOT_2016-04-29-16-01-10.war
BACKUPFILE=$BACKUPDIR/$BACKUPFILENAME
BASE_DIR=/usr/local/tomcat7
ARTIFACT_ID=yqms
DSTDIR=$BASE_DIR/webapps/yqms

mv $BACKUPDIR/yqms.war $BACKUPFILE

echo "INFO: BASE_DIR=$BASE_DIR"
TOMCAT_PID=`ps -ef|grep "$BASE_DIR.*start"|grep -v grep`
echo "INFO: TOMCAT PROCESS:$TOMCAT_PID"

TOMCAT_PID=`ps -ef|grep "$BASE_DIR.*start"|grep -v grep|awk '{print $2}'`
echo "INFO: TOMCAT_PID=$TOMCAT_PID"
# try stopping tomcat
if [ -n "$TOMCAT_PID" ] && [ $TOMCAT_PID -ge 0 ]; then
  echo "INFO: Trying to stop tomcat"
  sh $BASE_DIR/bin/catalina.sh stop
  echo "INFO: Wait for tomcat stop completely..."
  sleep 6s

  # force to close tomcat
  TOMCAT_PID=`ps -ef|grep "$BASE_DIR.*start"|grep -v grep|awk '{print $2}'`
  if [ -n "$TOMCAT_PID" ] && [ $TOMCAT_PID -ge 0 ]; then
    echo "INFO: catalina stop fail!!!!!"
    echo "INFO: Force stop tomcat with PID:$TOMCAT_PID"
    kill -9 $TOMCAT_PID
    echo "INFO: tomcat force shutdown"
  else 
    echo "INFO: Tomcat shutdown normally and gracefully"
  fi
else 
 echo "INFO: No tomcat running"
fi
# parse artifact id
if [ -z "$ARTIFACT_ID" ]; then
  echo "ERR: No ARTIFACT_ID defined, Please define ARTIFACT_ID in env.sh file"
  exit 2
fi
echo "INFO: ARTIFACT_ID=$ARTIFACT_ID"
if test -f $BACKUPFILE; then
  rm -rf $DSTDIR
  echo "INFO: $DSTDIR removed"
  mkdir $DSTDIR
  echo "INFO: Start copying file:$BACKUPFILE > $DSTDIR"
  cp $BACKUPFILE $DSTDIR
  if [ $? -eq 0 ]; then 
    echo "INFO: Deploy copy success: $BACKUPFILE > $DSTDIR"
  else 
    echo "ERR: Deploy copy fail:$BACKUPFILE > $DSTDIR"
    exit 1
  fi

  source /etc/profile ;cd $DSTDIR ;jar xf $BACKUPFILENAME
  if [ $? -eq 0 ]; then
    echo "INFO: Unarchieve $BACKUPFILENAME success"
  else
    echo "ERR: Unarchieve $BACKUPFILENAME fail"
    exit 1
  fi
  # remove war file
  rm -f $BACKUPFILENAME
else 
  echo "ERR: $BACKUPFILE not found, deploy fail"
  exit 1
fi 
echo "======================= DEPLOY SUCCESS ======================="
echo "INFO: Ready to start tomcat..."
source /etc/profile ;sh $BASE_DIR/bin/catalina.sh start &


