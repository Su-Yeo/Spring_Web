package com.coordination.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JythonController {
	
	private static final Logger logger = LoggerFactory.getLogger(JythonController.class);
	private String[] img = null;
	
	@Resource(name="imgPath")
	private String imgPath;
	
	@RequestMapping(value = "parsing")
	public String Tensorflow(Model model, HttpServletRequest request) throws Exception  {
			
		//이미지 분석 객체 생성
		Tensorflow tf = new Tensorflow();
		
		//이미지 분석 Path && 분석할 이미지명
		String path = "C:\\img\\tensorflow\\";
		String image = null;
		
		//파싱해서 온 이미지를 C:\img\tensorflow 폴더에 저장
		ImageDown(request);
		
		/*
		//이동할 폴더
		File folder = new File("C:\\img\\tensorflow");
		//이동될 폴더
		File folder2 = new File("C:\\img\\admin");
		
		//DB에 등록되지않은 tensorflow폴더 안에 있는 이미지명 추출
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++)
		{
			if(listOfFiles[i].isFile())
			{
				//Tensorflow폴더 안에 이미지파일명을 차례대로 image에 할당
				image = path + listOfFiles[i].getName();
				
				//전신 이미지 분리
				//tf.Cut(image);
				//상의 이미지 분석
				tf.Upper_Tensorflow(image);
				//하의 이미지 분석
				//tf.Lower_Tensorflow(image);
				//이미지 복구
				//tf.restore(image);	
			}
			else
			{
				System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
				System.out.println(listOfFiles[i].getName() + "파일이 존재하지 않습니다.");
				System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
			}
		}
		//Tensorflow에서 admin폴더로 이미지 복사
		copy(folder, folder2);
		System.out.println("======이미지 복사 완료======");
				
		//Tensorflow폴더 안에 이미지 삭제
		delete(folder.toString());
		System.out.println("======이미지 삭제 완료======");
		*/
		
		model.addAttribute("url", "insertStyle");
		
		return "movePage";
	}

	public void ImageDown(HttpServletRequest request) {
		
		//1 : Outer
		//2 : T-shirt
		//3 : Half-Tshirt
		//4 : Hood
		String imgName="";
		URL imageUrl = null;
        InputStream in = null;
        FileOutputStream fos = null;
        int data=0;
        String shopName = request.getParameter("shopName");
        int category = Integer.parseInt(request.getParameter("Category"));

		//파싱해서 가져온 이미지를 배열로 받는다.
		Parsing(shopName, category);

        //폴더 생성
  		File fileDir = new File(imgPath, "admin");
  		if(!fileDir.isDirectory())
  		{
  			fileDir.mkdirs();
  		}
      		
        try {       
        	
			//해당 로직에서 지정된 장소에 해당 이미지 다운로드
        	for(int i=0; i<img.length; i++)
			{
				imgName=img[i].substring(img[i].lastIndexOf('/') + 1, img[i].length());

				//URL로 이미지 가져오기
				imageUrl = new URL(img[i].toString());
				in = new BufferedInputStream(imageUrl.openStream());
				
				//다운로드
				fos = new FileOutputStream(imgPath+"\\admin\\"+imgName);
				while(true)
				{
	                //이미지를 읽어온다.
	                data = in.read();
	                if(data == -1)
	                {
	                    break;
	                }
	                //이미지를 쓴다.
	                fos.write(data);
	            }
	            in.close();
	            fos.close();
	            data=0;
			}
        	
        }catch (Exception e) {
			System.out.println("**********Error!!(ImageDown())**********");
		}	
	}

	//이미지 파싱
	public void Parsing(String shopName, int category) {
		
		//파싱할 웹 페이지
		String url = null;
		try {
			
			if(shopName.equals("언더70"))
			{
				switch(category)
				{
					//outer
					case 1:
						url = "http://under70.kr/product/list.html?cate_no=24&page=1";
						break;
					//t-shirt
					case 2:
						url = "http://under70.kr/product/list.html?cate_no=53&page=1";
						break;					
					//half-tshirt
					case 3:
						url = "http://under70.kr/product/list.html?cate_no=55&page=1";
						break;						
					//Hood
					case 4:
						url = "http://under70.kr/product/list.html?cate_no=33";
						break;						
					default:
						url = "null";
						break;
				}
				//Connect
				Document doc = Jsoup.connect(url).get();
				
				//상품리스트의 상품사진 class명 (수정O)	
				Elements imgs = doc.select("ul.prdList.column4 div.box a img.thumb"); 
				img = new String[imgs.size()];
				  
				for(int n=0; n<img.length; n++)
				{
					img[n] = "http:"+imgs.get(n).attr("src");
				}			
				
			}
			else if(shopName.equals("바이슬림"))
			{
				switch(category) 
				{
					//outer
					case 1:
						url = "https://www.byslim.com/category/outer/5/";
						break;
					//t-shirt
					case 2:
						url = "https://www.byslim.com/category/%EA%B8%B4%ED%8C%94-%ED%8B%B0%EC%85%94%EC%B8%A0/444/";
						break;
					//half-tshirt
					case 3:
						url = "https://www.byslim.com/category/%EB%B0%98%ED%8C%94/171/";
						break;
					//Hood
					case 4:
						url = "https://www.byslim.com/category/%EB%A7%A8%ED%88%AC%EB%A7%A8%ED%9B%84%EB%93%9C/72/";
						break;
					default:
						url = "null";
						break;
				}
				//Connect
				Document doc = Jsoup.connect(url).get();
				
				//상품리스트의 상품사진 class명 (수정O)	
				Elements imgs = doc.select("div.-thumb img");
				//1차적으로 src를 할당하기 위한 배열
				String[] str = new String[imgs.size()];
				//src에서 jpg또는 jpeg만을 할당하기 위한 List
				//jpg또는 jpeg만 할당한 후, 맨 위에 선언된 img[]에 리턴
				List<String> length = new ArrayList<String>();
				
				for(int n = 0; n < str.length; n++)
				{
					str[n] = imgs.get(n).attr("src");
					if(str[n].substring(str[n].length()-4, str[n].length()).equals(".jpg") ||
							str[n].substring(str[n].length()-4, str[n].length()).equals("jpeg"))
					{
						//jpg 또는 jpeg파일만 List에 할당
						length.add("http:" + str[n]);
					}
				}
				
				//jpg or jpeg 숫자만큼 배열 재선언
				img = new String[length.size()];
				for(int i=0; i<length.size(); i++)
				{
					img[i] = length.get(i);
				}
			}
		
		}catch(Exception e) {
			System.out.println("**********Error!! (Parsing())**********");
		}
	}
	
	//C:\img\tensorflow에서 C:\img\admin으로 이동
	public static void copy(File sourceF, File targetF){
		File[] target_file = sourceF.listFiles();
		
		for (File file : target_file)
		{
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			if(file.isDirectory())
			{
				temp.mkdir();
				copy(file, temp);
			} 
			else
			{
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp);
					byte[] b = new byte[4096];
					int cnt = 0;
					
					while((cnt=fis.read(b)) != -1)
					{
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					try {
						fis.close();
						fos.close();
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}
			}
		}
	}
	
	//C:\img\tensorflow 사진 삭제
	public static void delete(String path) {
		
		File folder = new File(path);
		try {
			if(folder.exists())
			{
			    File[] folder_list = folder.listFiles();
					
			    for (int i = 0; i < folder_list.length; i++)
			    {
			    	if(folder_list[i].isFile())
			    	{
			    		folder_list[i].delete();
			    	}
			    	else
			    	{
			    		delete(folder_list[i].getPath());
			    	}
			    	folder_list[i].delete();
			    }
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
