package com.zlsu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Wh {

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey",
					"fbcbd02ce99286ab00848dedcfe5846c");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String httpUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
		String httpArg = "cityid=101180801";
		String jsonResult = request(httpUrl, httpArg);
		JSONObject jsonObject = JSON.parseObject(jsonResult);
		jsonObject = jsonObject.getJSONObject("retData");
		JSONObject jsonObject1 = jsonObject.getJSONObject("today");
		JSONArray list = jsonObject.getJSONArray("forecast");
		System.out.println(jsonObject1);
		JSONObject jsonObject2 = JSONObject.parseObject(list.get(0).toString());
		System.out.println(jsonObject2);
		System.out.println(jsonObject1.get("curTemp"));

		//System.out.println(jsonResult);
		 //System.out.println(jsonObject);
		System.out.println(jsonResult);
	}

}
