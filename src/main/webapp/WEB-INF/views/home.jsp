<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page session="false"%>
<tags:template>
	<jsp:attribute name="breadcrumb">Home</jsp:attribute>

	<jsp:body>
		<c:url var="startProcess" value="/startProcess" />
		<table border="0">		
		<tr>
		  <td align="center"><input type="button"
					value="Step 1: Load Data" onclick="startDataLoading()" />
					
			  	
		    <input type="button" value="Step 2: Start Process"
					onclick="startMDProcess()" />
			</td>
			       		    		    		    
		</tr>
		</table>
        <br />
	</jsp:body>
</tags:template>