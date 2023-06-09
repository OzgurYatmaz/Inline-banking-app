<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">

		<h1>Enter User details:</h1>
		<form:form action="addClient" method="post" modelAttribute="client">
		
		<form:input type="hidden" path="id" />
		
			<fieldset class="mb-3">
				<form:label path="name">Name</form:label>
				<form:input type="text" path="name" required="required" />
				<form:errors path="name" cssClass="text-warning" />
			</fieldset>

			<fieldset class="mb-3">
				<form:label path="email">Email</form:label>
				<form:input type="text" path="email" required="required" />
				<form:errors path="email" cssClass="text-warning" />
			</fieldset>
			
			<fieldset class="mb-3">
				<form:label path="password">password</form:label>
				<form:input type="text" path="password" required="required" />
				<form:errors path="password" cssClass="text-warning" />
			</fieldset>

			<fieldset class="mb-3">
				<form:label path="mobileNumber">Mobile Number</form:label>
				<form:input type="text" path="mobileNumber" required="required" />
				<form:errors path="mobileNumber" cssClass="text-warning" />
			</fieldset>
			
			
			 
			<input type="submit" class="btn btn-success" />
		</form:form>
	</div>
<%@ include file="common/footer.jspf" %>

 

