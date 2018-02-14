<%@page import="org.apache.tools.ant.types.resources.First"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.* " %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%

String univers1 = (String)request.getAttribute("univer1");
String univers2 = (String)request.getAttribute("univer2");
String univers3 = (String)request.getAttribute("univer3");

String univers = (String)request.getAttribute("univer");

ArrayList<String> monuments1 = (ArrayList<String>)request.getAttribute("monum1");
ArrayList<String> monuments2 = (ArrayList<String>)request.getAttribute("monum2");
ArrayList<String> monuments3 = (ArrayList<String>)request.getAttribute("monum3");

HashMap<String,Double> FirstCountryPrices = (HashMap<String,Double>)request.getAttribute("Country1Prices");
HashMap<String,Double> SecondCountryPrices = (HashMap<String,Double>)request.getAttribute("Country2Prices");
HashMap<String,Double> ThirdCountryPrices = (HashMap<String,Double>)request.getAttribute("Country3Prices");


%>



<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Show University ranking, Tourism and living price data</title>
 </head>
 <body>
 <header>
	<form action="/index.jsp" method="post">
		  	<input type="submit" value="Back to the start">
	</form>
  	<h1>STUDENT EXCHANGE ADVISOR</h1>
  </header>
  <section class="contenidoUniversidad">
  <h3 class="TitlePrice">Data University</h3>
  <h3>University: <%=univers1%></h3>
  <h3>Ranking: <%=request.getAttribute("ranking1")%></h3>
  	  <section class="Precios">
		  <h3 class="TitlePrice">Prices Country</h3>
		  	<%
				for (String key : FirstCountryPrices.keySet()) {
			%>		
					<h3>Item: <%=key%></h3> <h3>Price: <%=FirstCountryPrices.get(key)+"$" %> </h3>
			<%	
				}	
		  	%>
      </section>
	  <section class="contenidoMonumentos" >
	  <%if(monuments1 != null) {%>
	  	<table >
	  		<thead>
				<tr class ="TableHeaderTr"> 
					<th>Monuments1</th>
				</tr>
			</thead>	
			<tbody class = "MonumentTableBody" >
				<%for(int i = 0; i < monuments1.size(); i+=2 ){%>
					<tr>
						<td><a href=<%=monuments1.get(i+1) %>><%=monuments1.get(i)%></a></td>
					</tr>
				<%}%>
			</tbody>
		</table>
		<%} %>
	  </section>
  </section>
  <section class="contenidoUniversidad">
  <h3 class="TitlePrice">Data University</h3>
  <h3>University: <%=univers2%></h3>
  <h3>Ranking: <%=request.getAttribute("ranking2")%></h3>
	  <section class="Precios">
		  <h3 class="TitlePrice">Prices Country</h3>
		  	<%
				for (String key : SecondCountryPrices.keySet()) {
			%>		
					<h3>Item: <%=key%></h3> <h3> Price: <%=SecondCountryPrices.get(key)+"$" %> </h3>
			<%	
				}	
		  	%>
  	  </section>
	  <section class="contenidoMonumentos" >
	  <%if(monuments2 != null) {%>
	  	<table >
	  		<thead>
				<tr class ="TableHeaderTr"> 
					<th>Monuments2</th>
				</tr>
			</thead>	
			<tbody class = "MonumentTableBody" >
				<%for(int i = 0; i < monuments2.size(); i+=2 ){%>
					<tr>
						<td><a href=<%=monuments2.get(i+1) %>><%=monuments2.get(i)%></a></td>
					</tr>
				<%}%>
			</tbody>
		</table>
		<%} %>
	  </section>
  </section>
  <section class="contenidoUniversidad">
  <h3 class="TitlePrice">Data University</h3>
  <h3>University: <%=univers3%></h3>
  <h3>Ranking: <%=request.getAttribute("ranking3")%></h3>
  <section class="Precios">
		  <h3 class="TitlePrice">Prices Country</h3>
		  	<%
				for (String key : ThirdCountryPrices.keySet()) {
			%>		
					<h3>Item: <%=key%></h3> <h3> Price: <%=ThirdCountryPrices.get(key) +"$"%> </h3>
			<%	
				}	
		  	%>
	</section>
	  <section class="contenidoMonumentos" >
	  <%if(monuments3 != null) {%>
	  	<table >
	  		<thead>
				<tr class ="TableHeaderTr"> 
					<th>Monuments3</th>
				</tr>
			</thead>	
			<tbody class = "MonumentTableBody" >
				<%for(int i = 0; i < monuments3.size(); i+=2 ){%>
					<tr>
						<td><a href=<%=monuments3.get(i+1) %>><%=monuments3.get(i)%></a></td>
					</tr>
				<%}%>
			</tbody>
		</table>
		<%} %>
		</section>
  </section>
 </body>
</html>

<style>
a{
    text-decoration:none;
    color: black;
}
.contenidoMonumentos {
    float: left;
    margin-top: 15%;
}

header{
		background-color: #fbad50;
		height: 100px;
		text-align: center;
		border: 1px solid gray;
}

h1{
		color: #cd486b;
}

.contenidoVida {
	float: right;
    width: 20%;
}

.contenidoUniversidad{
	width: 30%;
	float: left;
	margin: 1%;
}

table{
	text-align: center;
	font-family:arial, sans-serif; 	

}
.TableHeaderTr{
	text-align: center;
	color: #cd486b;
	position: absolute;
    background-color: #fbad50;
    border: 1px solid #cd486b;
}
.MonumentTableBody{
	overflow-y: scroll;
    height: 200px;
}

td{
	border: 1px solid gray;
	text-align:left;
	padding:8px;
}

form{
	float: left;
	margin: 2%;
	padding: 0;
}
table, tbody,thead , th, td {
    display: block;
}
thead, th{
    position: absolute;
    background-color: #fbad50;
    text-align: center;
	color: #cd486b;
	border: 1px solid #cd486b;
}

input{
	border-color: #cd486b;
	background-color: #fbad50;
	color: #cd486b;
}


tr { border: 1px solid #ccc; }

td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
}

td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
}
tbody:before {
    content: "-";
    display: block;
    line-height: 2em;
    background-color: white;
    color : black;
}

.Precios{
	margin-top: 15%;
	border-bottom: 2px solid gray;
	border-top: 2px solid gray;
}

.TitlePrice{
	text-align: center;
	color: #cd486b;
	background-color: #fbad50;
	border: 1px solid #cd486b;
}
</style>
