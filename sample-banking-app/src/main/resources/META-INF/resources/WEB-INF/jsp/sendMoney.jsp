<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">

		<h1>Send Money to ${name}:</h1>
		
		<form:form action="sendMoney" method="post" modelAttribute="transaction">
		<fieldset class="mb-3">
				<form:label path="amount">Amount to Send</form:label>
				<form:input type="number" path="amount" required="required" />
				<form:errors path="amount" cssClass="text-warning" />
			</fieldset>
			<fieldset class="mb-3">
				<form:label path="receiverId">Receiver Id</form:label>
				<form:input type="number" path="receiverId" required="required" />
				<form:errors path="receiverId" cssClass="text-warning" />
			</fieldset>
			<fieldset class="mb-3">
				<form:label path="senderId">Sender Id</form:label>
				<form:input type="number" path="senderId" required="required" />
				<form:errors path="senderId" cssClass="text-warning" />
			</fieldset>
			
			<input type="submit" class="btn btn-success" />
		</form:form>
 
	</div>
<%@ include file="common/footer.jspf" %>

</script>	


