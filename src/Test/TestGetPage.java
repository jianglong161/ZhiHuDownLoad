package Test;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;



public class TestGetPage {
	final static int BUFFER_SIZE = 4096;
	public TestGetPage(){}
	/**
	 * ���ҳ��Դ����
	 * 
	 * @return
	 */
	public String getWebPage(String baseuri) {

		String string = "";
		HttpClient httpClient = new HttpClient();
		// ����HttpClient�����ӹ����������� HTTP���ӳ�ʱ5s
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		 //�÷�����֪������ԴΪ�����
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36");	
		
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);
		GetMethod getMethod = new GetMethod(baseuri);
		// ������ʲô��
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		//����fire fox��http��������http�����ͷ��Ϣ���ѻ�������ӵ���Ϣ
		getMethod.addRequestHeader("Referer","http://www.zhihu.com/people/gong-xiao-wei/followees");
		getMethod.addRequestHeader("X-Requested-With","XMLHttpRequest");
		getMethod.addRequestHeader("Origin","http://www.zhihu.com");
		getMethod.addRequestHeader("Host","www.zhihu.com");
		getMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		getMethod.addRequestHeader("Cookie","_za=e152be18-1ffe-4231-a329-a4552c0efbd3; q_c1=b51f9f918815456bbed34237ea5dfca8|1438085254000|1438085254000; cap_id=\"MjM1MjI5N2MzODk1NDg2M2FjMDZhNjQ3NWZkZTQzMWE=|1438085254|3683d09865d655fd5fa4bde47aa92fb370949523\"; z_c0=\"QUFCQXFQNGlBQUFYQUFBQVlRSlZUWW45M2xVdkNzMTFsSFJudjJnWHdlQzhsQzZlYVR0XzlRPT0=|1438085257|f99e7b6edf3bd2043487071f0ea6ff11c227637a\"; _xsrf=2fd8c9059d757ce6d4770c7ea46983a1; tc=AQAAAFxnuR02twgAjLj3cPF3c9s0Lebx; __utmt=1; __utma=51854390.1379204766.1438085205.1438778718.1438859888.3; __utmb=51854390.27.9.1438862846996; __utmc=51854390; __utmz=51854390.1438085205.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.100-1|2=registration_date=20131226=1^3=entry_date=20131226=1");
		
		
		
		try {

			int statut = httpClient.executeMethod(getMethod);
			if (statut != HttpStatus.SC_OK)
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			else {
				try {
					InputStream content = getMethod.getResponseBodyAsStream();
					string = InputStreamTOString(content, "UTF-8");
					// System.out.println(baseuri + "::" + string);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("InputStreamתString���ִ���...");
					return "";
				}
			}
		} catch (HttpException e) {
			System.err.println("getmethod error");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("io error");
			e.printStackTrace();
		}

		return string;
	}

	/**
	 * ��InputStreamת����ĳ���ַ������String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding)
			throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return new String(outStream.toByteArray(), encoding);
	}

	public void testRegex(String sourse) {
		// //ͨ���ַ��������������ʽ��ģʽ
		// Pattern pattern=Pattern.compile("");
		// //�����������ʽ���ַ�����ʽ
		// pattern.pattern();
		//
		// Pattern p=Pattern.compile("\\d+");
		// Matcher
		// m=p.matcher("�ҵ�QQ��:456456 �ҵĵ绰��:0532214 �ҵ�������:aaa123@aaa.com");
		// while(m.find()) {
		// System.out.println(m.group()+"\tstart: "+m.start()+"\tend: "+m.end());
		// }
//		Pattern p = Pattern
//				.compile("(?<=(href=\")).*?(?=\")");
//		1��ƥ��html��img��ǩ������<img.*src=(.*?)[^>]*?>
//		2��ƥ��img��ǩ�е�src��http·��������http:\"?(.*?)(\"|>|\\s+)
		System.out.println(sourse);
		sourse=sourse.replaceAll("\n", "\r\n");
		fileWriter(sourse);
//		Pattern p=Pattern.compile("<img.*src=(.*?)[^>]*?>");
//		Matcher m = p.matcher(sourse);
//		while (m.find()) {
//			System.out.println(m.group());
//		}
	}
	public void fileWriter(String source)
	{
		try {
			FileWriter fWriter=new FileWriter("text.txt");
			fWriter.write(source);
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestGetPage mt = new TestGetPage();
		// mt.getWebPage();
		mt.testRegex(mt.getWebPage("http://www.zhihu.com/node/ProfileFolloweesListV2?method=next&params=%7B%22offset%22%3A20%2C%22order_by%22%3A%22created%22%2C%22hash_id%22%3A%22d00ade9168907740fcea35fa5fbc7ace%22%7D&_xsrf=2fd8c9059d757ce6d4770c7ea46983a1"));
	}

}