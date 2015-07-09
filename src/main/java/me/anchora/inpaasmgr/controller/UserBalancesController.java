package me.anchora.inpaasmgr.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalances;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalancesCriteria;
import me.anchora.inpaasmgr.service.UserBalancesService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.DateUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;
import me.anchora.inpaasmgr.utils.RequestUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.bytecode.opencsv.CSVWriter;

@Controller
public class UserBalancesController extends BaseController {
	private static Logger logger = Logger.getLogger(UserBalancesController.class);
    @Autowired
    private UserBalancesService userBalancesService;

    @RequestMapping(value = "/admin/getAllUserBalancesByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllUserBalancesByPage(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();

        try {
        	UserBalances userBalances = (UserBalances) EntryUtil.getObject(request, UserBalances.class);
        	logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        	logger.info(userBalances);
        	//下面的criteria是根据上面相应的request请求(前端的请求request)创建符合要求的查询语句，如：搜索充值类型为“系统赠送”等等。。。。。。,请求不同则搜索结果不同。
        	//下面的userBalances即为对应UserBalances userBalances = (UserBalances) EntryUtil.getObject(request, UserBalances.class)中的userBalances;请求（request）的userBalances
        	//例如：若是点击对应页面触发，则查询所有useralances数据，得到下面的Integer count = userBalancesService.queryCount(criteria)中的count=37
        	//搜索充值类型为“系统赠送”，则count=32;搜索充值类型为“系统赠送”且用户email为“812163445@qq.com”,则count=1.
        	UserBalancesCriteria criteria = (UserBalancesCriteria)CriteriaUtil.createCriteria(userBalances, UserBalancesCriteria.class);
            PageTool.pageSetting(userBalances, cacheService);
            //Myatis的RowBounds用于分页处理   userBalances.getRowBounds()
            List<UserBalances> resultList = userBalancesService.queryUserBalancesByPage(criteria, userBalances.getRowBounds());
            Integer count = userBalancesService.queryCount(criteria);
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info(count);
    
            result.addList(resultList, getTimezone(request));//BaseController.java中有getTimezone(HttpServletRequest request)方法
            result.addTotalPage(PageTool.pageCount(count, userBalances.getPageSize()));
            logger.info(userBalances.getPageSize());
            result.addCurrentPage(userBalances.getCurrentPage());
            logger.info(userBalances.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    

    @RequestMapping(value = "/admin/getAllUserBalances.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllUserBalances(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
        	UserBalances userBalances = (UserBalances) EntryUtil.getObject(request, UserBalances.class);
        	UserBalancesCriteria criteria = (UserBalancesCriteria)CriteriaUtil.createCriteria(userBalances, UserBalancesCriteria.class);

            List<UserBalances> resultList = userBalancesService.queryAllUserBalances(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
  @RequestMapping(value = "admin/exportUserBalancesCSV.html", method = { RequestMethod.POST, RequestMethod.GET })
  public void exportCSVFile(HttpServletRequest request, HttpServletResponse response) {
  
//      PaasResult result = new PaasResult();
      try {
        String email=request.getParameter("email");
        String balanceType= request.getParameter("balanceType");
        UserBalances userBalances=new UserBalances();
        userBalances.setEmail(email);
        userBalances.setBalanceType(balanceType);
        UserBalancesCriteria criteria=(UserBalancesCriteria) CriteriaUtil.createCriteria(userBalances, UserBalancesCriteria.class);
        
        List<UserBalances> resultList=userBalancesService.queryAllUserBalances(criteria);
        List<UserBalances> nvlResultList=nvlParameter(resultList);
        
        List<String[]> dataList=new ArrayList<String[]>();
        Locale locale=RequestUtil.getLocale(request);
        ResourceBundle rb = ResourceBundle.getBundle("i18n/messages", locale);
        String[] header={rb.getString("label.email"),
                rb.getString("label.Balance"),
                rb.getString("label.BalanceType"),
                rb.getString("label.CreatedAt"),
                rb.getString("label.UpdateAt"),
                rb.getString("label.remark")};
        for(int i=0;i<nvlResultList.size();i++){
            String [] s ={nvlResultList.get(i).getEmail(),
                    nvlResultList.get(i).getBalance().toString(),
                    getBalanceTypeName(nvlResultList.get(i).getBalanceType()+"", rb),
                    DateUtil.format(nvlResultList.get(i).getCreatedAt())+"",
                    DateUtil.format(nvlResultList.get(i).getUpdatedAt())+"",
                    nvlResultList.get(i).getRemark()};
            dataList.add(s);
        }
        String filePath = request.getSession().getServletContext().getRealPath("/");
        filePath=filePath+"upload"+File.separator+new SimpleDateFormat("yyyyMMdd").format(new Date());
        File uploadFolder=new File(filePath);
        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }
        String fileName=new SimpleDateFormat("HHmmss").format(new Date());
        File file=new File(filePath+File.separator+fileName+".csv");
        if(file.exists()){
            file.delete();
        }else{
            file.createNewFile();
        }
        FileOutputStream fos=new FileOutputStream(file);
          // write UTF8 BOM mark if file is empty
        if(file.length()<1){
              final byte[] bom = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF };
                fos.write(bom);
        }
        OutputStreamWriter out = new OutputStreamWriter(fos, Charset.forName("utf-8"));
        CSVWriter writer=new CSVWriter(out,',');
        writer.writeNext(header);
        writer.writeAll(dataList);
        writer.close();
        zipFile(filePath+File.separator+fileName);
          downloadFile(filePath+File.separator+fileName, request, response);
      } catch (Exception e) {
          super.exception(request, e);
      }
  }
  
  
  
  
  private List<UserBalances> nvlParameter(List<UserBalances> resultList){
      List<UserBalances> nvlList= new ArrayList<UserBalances>();
      for(UserBalances ub:resultList){
          UserBalances userBalances=new UserBalances();
          String email=ub.getEmail()==null?"--":ub.getEmail();
//          String balance=(String) (ub.getBalance()==null?"--":ub.getBalance());
          String balanceType=ub.getBalanceType()==null?"--":ub.getBalanceType();
          String remark=ub.getRemark()==null?"--":ub.getRemark();
          userBalances.setEmail(email);
          userBalances.setBalance(ub.getBalance());
          userBalances.setBalanceType(balanceType);
          userBalances.setCreatedAt(ub.getCreatedAt());
          userBalances.setUpdatedAt(ub.getUpdatedAt());
          
          userBalances.setRemark(remark);
          nvlList.add(userBalances);
      }
      return nvlList;
  }

private static String getBalanceTypeName(String flag,ResourceBundle rb){
      String name="--";
      if("0".equals(flag)){
          name=rb.getString("BALANCETYPE_SYS_PRESENTED");
      }else if("1".equals(flag)){
          name=rb.getString("BALANCETYPE_USER_CHARGING");
      }else if("2".equals(flag)){
          name=rb.getString("BALANCETYPE_SHOP_INCOME");
      }
      
      return name;
    }

  
private static void zipFile(String filePath) throws IOException{
  File zipFile = new File(filePath + ".zip");
      if (zipFile.exists()) {
          zipFile.delete();
      }
      ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(zipFile));
      File f = new File(filePath+".csv");
      byte[] buf = null;
      zipout.putNextEntry(new ZipEntry(f.getName()));
      FileInputStream fileInputStream = new FileInputStream(f);
      buf = new byte[2048];
      BufferedInputStream origin = new BufferedInputStream(fileInputStream,2048);
      int len;
      while ((len = origin.read(buf,0,2048))!=-1) {
        zipout.write(buf,0,len);
          }
      zipout.flush();
      origin.close();
      fileInputStream.close(); 
      zipout.flush();
      zipout.close();
}

private static void downloadFile(String filePath,HttpServletRequest request, HttpServletResponse response){
  FileInputStream inputStream = null;
    OutputStream outputStream =null; 
    File file = new File(filePath+".zip");
  try {
      StringBuffer sb = new StringBuffer();
      sb.append("attachment;  filename=").append(filePath+".zip");
      response.reset();
      response.setHeader("Expires", "0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/x-msdownload;charset=UTF-8");
        response.setHeader("Content-Disposition", new String( sb.toString().getBytes(), "ISO8859-1"));
        inputStream = new FileInputStream(file);
        outputStream = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        } 
        outputStream.flush();
      outputStream.close();
      inputStream.close(); 
} catch (Exception e) {
    e.printStackTrace();
}
  
}
}

