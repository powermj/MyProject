package com.zhxg.framework.base.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class UploadFileUtil {

	private final String url = PropertiesUtil.getValue("SMB_URL");
	private final String EVENTANALYSISSNAPSHOTURL = PropertiesUtil.getValue("EVENTANALYSISSNAPSHOT_URL");
	private SmbFile smbFile = null;
	
	public static Map<String, String> downInProgress = new HashMap<>();  
	
    /******** 文件服务器保存文件路径 **************************/
	private static final String PATH_PICTURE = "photos";
	
	private static final String PATH_VIDEO = "videos";
	
	private static final String PATH_OTHER = "others";
	
	private static final Set<String> PATH_PICTURE_KEY;
	
	private static final Set<String> PATH_VIDEO_KEY;
	
    private static UploadFileUtil smb = null; // 共享文件协议
	

	static{
        // 默认的图片格式
		PATH_PICTURE_KEY = new HashSet<String>();
		PATH_PICTURE_KEY.add( "jpg" );

        // 默认的视频格式
		PATH_VIDEO_KEY = new HashSet<String>();
		PATH_VIDEO_KEY.add( "mp4" );
	}

    // 3. 得到Smb和连接的方法

	public static synchronized UploadFileUtil getInstance() {
		if (smb == null)
			smb = new UploadFileUtil();
		return smb;
	}

	                /**
     * @param url服务器路径
     */
	private UploadFileUtil() {
		this.init();
	}

	public void init() {
		try {
            System.out.println("开始连接...url：" + this.url);
			this.smbFile = new SmbFile(this.url);
			this.smbFile.connect();
            System.out.println("连接成功...url：" + this.url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.print(e);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print(e);
		}
	}
	
	public SmbFile getIn(String path){
		SmbFile smbFile = null;
		try {
			try {
				if(path.lastIndexOf("/")>0){
					this.createDir(path.substring(0,path.lastIndexOf("/")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			smbFile = new SmbFile(this.url+"/"+path);
			smbFile.connect();
		} catch (SmbException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return smbFile;
	}
	
	public SmbFile getInByFullPath(String url){
		SmbFile smbFile  = null;
		try {
			smbFile = new SmbFile(url);
			smbFile.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return smbFile;
	}
	
	public OutputStream getOut(String path){
		SmbFileOutputStream smbOut = null;
		try {
			try {
				if(path.lastIndexOf("/")>0){
					this.createDir(path.substring(0,path.lastIndexOf("/")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			smbOut = new SmbFileOutputStream(this.url + "/"
					+ path, false);
		} catch (SmbException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return smbOut;
	}
	public OutputStream getEventAnalysisSnapshotOut(String path){
        SmbFileOutputStream smbOut = null;
        try {
            try {
                if(path.lastIndexOf("/")>0){
                    this.createDir(path.substring(0,path.lastIndexOf("/")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            smbOut = new SmbFileOutputStream(this.EVENTANALYSISSNAPSHOTURL + "/"
                    + path, false);
        } catch (SmbException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return smbOut;
    }

    // 4. 上传文件的方法

	                /**
     * 上传文件到服务器
     */
	public int uploadFile(File file) {
		SmbFileOutputStream smbOut = null;
		int flag = -1;
		BufferedInputStream bf = null;
        FileInputStream fs = null;
		try {
			smbOut = new SmbFileOutputStream(this.url + "/"
					+ file.getName(), false);
            fs = new FileInputStream(file);
            bf = new BufferedInputStream(fs);
			byte[] bt = new byte[8192];
			int n = bf.read(bt);
			while (n != -1) {
				smbOut.write(bt, 0, n);
				smbOut.flush();
				n = bf.read(bt);
			}
			flag = 0;
            System.out.println("文件传输结束...");
		} catch (SmbException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (UnknownHostException e) {
			e.printStackTrace();
            System.out.println("找不到主机...url：" + this.url);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			try {
                if (null != fs)
                    fs.close();
				if (null != smbOut)
					smbOut.close();
				if (null != bf)
					bf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return flag;
	}
	
	                /**
     * 上传文件到服务器
     */
	public int uploadFile(File file,String timestring) {
		SmbFileOutputStream smbOut = null;
		int flag = -1;
		BufferedInputStream bf = null;
        FileInputStream fs = null;
		try {
			try {
				this.createDir(timestring);
			} catch (Exception e) {
				e.printStackTrace();
			}
			smbOut = new SmbFileOutputStream(this.url + "/"+timestring+"/"
					+ file.getName(), false);
            fs = new FileInputStream(file);
            bf = new BufferedInputStream(fs);
			byte[] bt = new byte[8192];
			int n = bf.read(bt);
			while (n != -1) {
				smbOut.write(bt, 0, n);
				smbOut.flush();
				n = bf.read(bt);
			}
			flag = 0;
            System.out.println("文件传输结束...");
		} catch (SmbException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (UnknownHostException e) {
			e.printStackTrace();
            System.out.println("找不到主机...url：" + this.url);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			try {
                if (null != fs)
                    fs.close();
				if (null != smbOut)
					smbOut.close();
				if (null != bf)
					bf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return flag;
	}
	
	public int uploadFile(String urlString, String filename,String path) {
		int flag = -1;
		BufferedInputStream bf = null;
		SmbFileOutputStream smbOut = null;
		try {
			this.createDir(path);
			
			smbOut = new SmbFileOutputStream(this.url + "/"+
					path+"/"+ filename, false);
			URL url = new URL(urlString);  
            // 打开连接
	        URLConnection con = url.openConnection();  
            // 设置请求超时为5s
	        con.setConnectTimeout(5*1000);  
            // 输入流
	        InputStream is = con.getInputStream(); 
	        
	        int length = con.getContentLength();
	        
			bf = new BufferedInputStream(is);
			byte[] bt = new byte[8192];
			int n = bf.read(bt);
			if(n == -1){
				flag = 0;
			}
			while (n != -1) {
				smbOut.write(bt, 0, n);
				smbOut.flush();
				n = bf.read(bt);
			}
			flag = 1;
            System.out.println("文件传输结束...");
		} catch (SmbException e) {
			e.printStackTrace();
			flag = -1;
			System.out.println(e);
		} catch (MalformedURLException e) {
			flag = -1;
			e.printStackTrace();
			System.out.println(e);
		} catch (UnknownHostException e) {
			flag = -1;
			e.printStackTrace();
            System.out.println("找不到主机...url：" + this.url);
		} catch (IOException e) {
			e.printStackTrace();
			flag = -1;
			System.out.println(e);
		} catch (Exception e) {
			flag = -1;
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != smbOut)
					smbOut.close();
				if (null != bf)
					bf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return flag;
	}
	
    /**
     * 上传文件到服务器
     */
    public int uploadFile(InputStream inputStream, String timestring, String fileName, String url) {
        SmbFileOutputStream smbOut = null;
        int flag = -1;
        BufferedInputStream bf = null;
        try {
            try {
                this.createDir(timestring);
            } catch (Exception e) {
                e.printStackTrace();
            }
            smbOut = new SmbFileOutputStream(url + "/" + timestring + "/" + fileName, false);
            bf = new BufferedInputStream(inputStream);
            byte[] bt = new byte[8192];
            int n = bf.read(bt);
            while (n != -1) {
                smbOut.write(bt, 0, n);
                smbOut.flush();
                n = bf.read(bt);
            }
            flag = 0;
            System.out.println("文件传输结束...");
        } catch (SmbException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("找不到主机...url：" + this.url);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            try {
                if (null != bf) {
                    bf.close();
                }
                if (null != smbOut) {
                    smbOut.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return flag;
    }

	/**
	 * 
	 * @return file url in smb server 
	 */
	public String uploadFileToSmb(String inputUrl) throws Exception  {
	    System.out.println( "VideoAction.downloadToSmb()========================================================");
	    BufferedInputStream bf = null;
	    SmbFileOutputStream smbOut = null;
	    String smbUrl = null;
	    try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	        String path = this.choosePaths(inputUrl);
	        String fileName = this.generateFileName(inputUrl);
	        smbUrl = this.url + "/"+ path+"/"+ fileName;

	        this.createDir(path);
	        smbOut = new SmbFileOutputStream(smbUrl, false);
            URL url = new URL(inputUrl);  
	        // 打开连接
	        URLConnection con = url.openConnection();  
	        // 设置请求超时为5s
	        con.setConnectTimeout(5*1000);  
	        // 输入流
	        InputStream is = con.getInputStream(); 

	        bf = new BufferedInputStream(is);

	        byte[] bt = new byte[8192];
	        int n = bf.read(bt);
	        int point = 0;
	        while (n != -1) {
	            System.out.println(" bufferreda  == " + (point += n)  );
	            smbOut.write(bt, 0, n);    // save data to smb server
	            smbOut.flush();
	            n = bf.read(bt);
	        }
	        downInProgress.put(inputUrl, smbUrl);
	        System.out.println("文件传输结束...");
        } catch (SmbException e) {
	        e.printStackTrace();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (UnknownHostException e) {
	        System.out.println("找不到主机...url：" + this.url);
	        System.out.println("找不到主机...url：" + this.url);
	    } catch (IOException e) {
	        System.out.println("exception when down file to smb server");
	    }finally {
	        try {
	            if (null != smbOut)
	                smbOut.close();
	            if (null != bf)
	                bf.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    System.out.println("download to smb success");
	    return smbUrl;
	}

	public String getSmbUrl(String path, String fileName){
		return this.url + "/"+ path+"/"+ fileName;
	}
	
    // 5. 在main方法里面测试

	public void uploadFileToServer(String localFile) {
        // 服務器地址 格式為 smb://电脑用户名:电脑密码@电脑IP地址/IP共享的文件夹
		File file = new File(localFile);
        this.uploadFile(file);// 上传文件
	}

	public void createDir(String dir) throws Exception{  
		String[] str = dir.split("/");
		String newDir="";
		for (String string : str) {
		    newDir+=string+"/";
	        SmbFile fp = new SmbFile(this.url+"//"+newDir);  
	        //File fp = new File("Z://"+dir);    
            // 目录已存在创建文件夹
	        if (fp.exists() && fp.isDirectory()) { 
	        	
	        } else{  
                // 目录不存在的情况下，会抛出异常
	            fp.mkdir();    
	        }  
		}
    }
	

	/**
     * 获取文件名
     * 
     * @param url
     * @return
     */
    private String generateFileName(String url){
        String fileName = this.getFileNameByUrl(url);
        return fileName.substring(0, fileName.lastIndexOf(".")) + UUIDUtil.getUuid() + fileName.substring(fileName.lastIndexOf("."));
    }
	
	                /**
     * 根据url获取需要下载的文件名
     * 
     * @param videoSerUrl
     * @return
     */
	private String getFileNameByUrl(String videoSerUrl) {
		String fileName = "";
		if(videoSerUrl.lastIndexOf("/") >= 0){
			fileName = videoSerUrl.substring( videoSerUrl.lastIndexOf( "/") + 1);
		} 
		return fileName;
	}
	
	                /**
     * 获取文件对应的路径
     * 
     * @param url
     * @return
     */
	private String choosePaths(String url){
		String type = null;
		if(url != null &&  url.lastIndexOf(".") >= 0){
			type = url.substring( url.lastIndexOf(".") + 1 );
		}
		return this.getPath(type);
	}
	
	
	                /**
     * 根据文件类型选择适当的文件路径
     * 
     * @param type
     * @return
     */
	private String getPath(String type) {
		if(type == null){
			return PATH_OTHER;
		}
		
		type = type.trim();
		
		if(PATH_PICTURE_KEY.contains( type )){
			return PATH_PICTURE;
		}
		
		if(PATH_VIDEO_KEY.contains( type )){
			return PATH_VIDEO;
		}
		
		return PATH_OTHER;
	}


}
