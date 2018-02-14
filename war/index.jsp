<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->
<%@page import="com.*" %>
<%@page import="java.util.*" %>
<%@page import="java.io.IOException" %>
<%@page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@page import="javax.xml.parsers.DocumentBuilder" %>
<%@page import="org.jsoup.Jsoup " %>
<%@page import="org.jsoup.nodes.Element" %>
<%@page import="org.jsoup.helper.Validate" %>
<%@page import="org.jsoup.select.Elements" %>
<%@page import="org.jsoup.helper.Validate" %>
<%@page import="org.jsoup.nodes.Document" %>
<%@page import="java.io.BufferedReader" %>
<%@page import="java.io.File" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.IOException" %>
<%@page import="java.io.InputStreamReader" %>
<%@page import="java.io.Reader" %>
<%@page import="java.net.URL" %>
<%@page import="java.net.URLConnection" %>
<%@page import="java.nio.charset.Charset" %>
<%@page import="org.jsoup.Jsoup" %>
<%@page import="org.jsoup.nodes.Document" %>
<%@page import="org.jsoup.nodes.Element" %>
<%@page import="org.jsoup.select.Elements" %>
<%@page import="org.omg.CORBA.portable.InputStream" %>
<%@page import="org.json.*" %>
<%@page import="java.net.*" %>
<%@page import="java.io.*" %>
<%@page import="org.apache.commons.lang3.*" %>
<%@page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@page import="com.google.appengine.api.datastore.Text" %>
<%@page import="com.google.appengine.api.datastore.Entity" %>

<% 

HashMap<String,Integer> U_R;
DatabaseManagment dB = new DatabaseManagment();
Entity campo = dB.getEntityPage();

Text valor = (Text)campo.getProperty("Enlace");

String JSONUniversidades = valor.getValue();

UniversitiesRanking universities = new UniversitiesRanking();
universities.setMainPageURL("https://www.timeshighereducation.com");
universities.setSubjectURLExtention("/world-university-rankings/by-subject");
universities.setSubjectCompleteURL();
universities.getSubjectCompleteURL();
universities.loadTheContentSourceIntoDocument();
universities.SetClass_Name("is-new");
universities.SelectElementfromDoc();
universities.setSubjectMainLink("href=/world-university-rankings/2017/subject-ranking/");
universities.setTheDynamicTableJsonUrl("https://www.timeshighereducation.com//sites//default//files//the_data_rankings//computer_science_rankings_2017_limit0_4516931838992206229b45900bd0edde.json");
universities.ConvertTheJSONintoString();
if(!universities.getConvertIntoJson().contains("Not Found")){
	universities.GenerateJsonObjectAndArray();
}
else{	
	universities.realizeParcialWork(JSONUniversidades);
}


U_R = universities.getUniversity_Ranking(); 
	
%>
<html>
<head>

<style>
	html{
		padding: 0;
		margin: 0;
	}
	body{
		background-color: #dfe3ee;
	}
	header{
		background-color: #fbad50;
		height: 100px;
		text-align: center;
		border: 1px solid gray;
	}
	footer{
		margin-top: 2%;
		height: 82px;
		text-align: center;
		clear: both;
	}
	h1{
		color: #cd486b;
	}
	h4{
		color: #cd486b;
	}
	p{
		color: #cd486b;
	}
	select{
		width: 50%;
	}
	option{
		width: 50%;
	}
	section{
		text-align: center;
	}
	.logo{
		width: 500px;
		height: 100px;
		text-align: center;
	}
	.imagenErasmus{
		width: 100px;
		height: 100px;
		float: left;
	}
	.box{
		width:300px;
		height:200px;
		float:left;
		margin-left: 2%;
		border-radius: 2px;
		box-shadow: 5px 5px 5px #999;
		background-color: #fbad50;
	}
	.ext{
		width: 80%;
		margin-top: 2%;
		margin-left: 11%;
		height: 400px;
	}
	.fotoPerfil{
		width: 80px;
		height: 80px;
		margin-left: 5%;
		margin-right: 5%;
		border-radius: 150px;
		border: 1px solid black;
	}
	.title{
		text-align: center;
	}
	.parte{
		float: left;
		margin-top: 2%;
		margin-left: 20%;
		display: flex;
	}
	input{
		border-radius: 100px;
		margin: 5%;
		background-color: #e95950;
		border-color: #cd486b;
		padding: 1%;
	}
</style>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>STUDENT EXCHANGE ADVISOR</title>
	<script>
	function validateForm() {
	    var x = document.forms["myForm"]["university1"].value;
	    var y = document.forms["myForm"]["university2"].value;
	    var z = document.forms["myForm"]["university3"].value;
	    if (x == y && y == z) {
	        alert("Incorrect input data.");
	        return false;
	    }
	    else if (x == y) {
	        alert("Incorrect input data.");
	        return false;
	    }
	    else if (y == z) {
	        alert("Incorrect input data.");
	        return false;
	    }
	    else if (x == z) {
	        alert("Incorrect input data.");
	        return false;
	    }
	}
	</script>
</head>
<body>
  <header>
  <h1>STUDENT EXCHANGE ADVISOR</h1>
  </header>
  <section class="ext">
  			<h4>UNIVERSITIES</h4>
  			<form name="myForm" action="/iseprogram" method="post" onsubmit="return validateForm()">
			<select class="box" id = "university_id1" name="university1">
			<% 
				for (String key : U_R.keySet()) {
			%>		
				<option value = "<%=key %>"><%=key%></option>
			<%
				}
			%>
			</select> 
			<select class="box" id = "university_id2" name="university2">
			<% 
				for ( String key : U_R.keySet()) {
			%>		
				<option value = "<%=key %>"><%=key%></option>
			<%
				}
			%>
			</select>
			<select class="box" id = "university_id3" name="university3">
			<% 
				for (String key : U_R.keySet()) {
			%>		
				<option value = "<%=key %>"><%=key%></option>
			<%
				
				}
			%>
			</select>
  			<input type="submit" value="SEARCH">
		</form>
  </section>
  <footer>
  </footer>
</body>
</html>
