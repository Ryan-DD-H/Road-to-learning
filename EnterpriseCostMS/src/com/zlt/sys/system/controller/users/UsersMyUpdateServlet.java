package com.zlt.sys.system.controller.users;

import com.zlt.pojo.Users;
import com.zlt.sys.system.service.IUsersService;
import com.zlt.sys.system.service.imp.UsersServiceImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static com.zlt.utiles.NullAssertion.isNotNull;


@WebServlet("/system/UsersMyUpdateServlet")
public class UsersMyUpdateServlet extends HttpServlet {


    /*
    * 用户更新自己的用户信息
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            IUsersService iUsersService = new UsersServiceImp();
            //得到上传文件的保存目录。 将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
            String realPath = this.getServletContext().getRealPath("/upload");//  /WEB-INF/files
            String[] path = realPath.split("\\\\");
            String reativePath = "";
            for (int i = 0;i <= path.length-5;i++){
                reativePath += path[i] + "\\";
            }
            reativePath += "web\\resource\\assets\\images";
            //System.out.println("文件存放位置:" + reativePath);
            //设置临时目录。 上传文件大于缓冲区则先放于临时目录中
            String tempPath = "D:\\_tempPath";
            //System.out.println("临时文件存放位置:" + tempPath);


            //判断存放上传文件的目录是否存在（不存在则创建）
            File f = new File(realPath);
            if (!f.exists() && !f.isDirectory()) {
                System.out.println("目录或文件不存在! 创建目标目录。");
                f.mkdir();
            }
            //判断临时目录是否存在（不存在则创建）
            File f1 = new File(tempPath);
            if (!f1.isDirectory()) {
                System.out.println("临时文件目录不存在! 创建临时文件目录");
                f1.mkdir();
            }


            //设置环境:创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //设置上传文件的临时目录
            factory.setRepository(f1);

            //核心操作类:创建一个文件上传解析器。
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传"文件名"的中文乱码
            upload.setHeaderEncoding("UTF-8");

            //判断enctype:判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                System.out.println("不是上传文件，终止");
                //按照传统方式获取数据
                return;
            }

            //限制单个上传文件大小(5M)
            upload.setFileSizeMax(1024*1024*4);

            //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> items = upload.parseRequest(request);
            Users users = new Users();//用来接收数据的
            for (FileItem item : items) {
                //如果fileitem中封装的是普通输入项的数据（输出名、值）
                if (item.isFormField()) {
                    String filedName = item.getFieldName();//普通输入项数据的名
                    //解决普通输入项的数据的中文乱码问题
                    String filedValue = item.getString("UTF-8");//普通输入项的值
                    //将获取到的对应的值放入到Users中
                    if(filedName.equals("usersId")){
                        users.setUsersId(Integer.valueOf(filedValue));
                    }else if(filedName.equals("usersName")){
                        users.setUsersName(filedValue);
                    }else if(filedName.equals("usersAge")){
                        users.setUsersAge(Integer.valueOf(filedValue));
                    }else if(filedName.equals("usersSex")){
                        users.setUsersSex(filedValue);
                    }else if(filedName.equals("usersPhone")){
                        users.setUsersPhone(filedValue);
                    }else if(filedName.equals("usersPwd")){
                        users.setUsersPwd(filedValue);
                    }

                } else {
                    //判断是否是图片类型的文件
                    if(!item.getContentType().contains("image"))
                        isNotNull("请不要糊弄我，上传图片可以吗？",0);

                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();//上传文件的名
                    //多个文件上传输入框有空 的 异常处理
                    if (fileName == null || "".equals(fileName.trim())) {  //去空格是否为空
                        continue;// 为空，跳过当次循环，  第一个没输入则跳过可以继续输入第二个
                    }

                    //处理上传文件的文件名的路径，截取字符串只保留文件名部分。//截取留最后一个"\"之后，+1截取向右移一位
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    String[] type = fileName.split("\\.");
                    String str= RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890");

                    //拼接上传路径。存放路径+上传的文件名
                    String filePath = reativePath + "\\" + str + "." + type[1];
                    String reativeUrl = "resource/assets/images/"+str+"."+type[1];//字母绝对没拼错！！！
                    users.setProfileUrl(reativeUrl);
                    //构建输入输出流
                    InputStream in = item.getInputStream(); //获取item中的上传文件的输入流
                    OutputStream out = new FileOutputStream(filePath); //创建一个文件输出流

                    //创建一个缓冲区
                    byte b[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = -1;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))！=-1就表示in里面还有数据
                    while ((len = in.read(b)) != -1) {  //没数据了返回-1
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath+"\\"+filename)当中
                        out.write(b, 0, len);
                    }
                    //关闭流
                    out.close();
                    in.close();
                    //删除临时文件

                    item.delete();//删除处理文件上传时生成的临时文件
                    //System.out.println("文件上传成功");
                }
            }

            iUsersService.updateUsersInfo(users);
            request.setAttribute("tips","用户信息修改成功!");
            request.getSession().setAttribute("users",users);
            request.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(request,response);

        } catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(request,response);
        } catch (FileUploadException e) {
            request.setAttribute("tips","服务器繁忙，用户信息修改上传失败");
            request.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
