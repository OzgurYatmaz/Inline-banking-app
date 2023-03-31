    <%@ include file="common/header.jspf" %>
	<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<div>Welcome Mr ${name}</div>
		<hr>
		<h1>Your balance is ${balance} Dinar</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>MobileNumber</th>
					<th>Role</th>
					<th>Money</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clients}" var="client">
					<tr>
						<td>${client.name}</td>
						<td>${client.email}</td>
						<td>${client.mobileNumber}</td>
						<td>${client.role}</td>
						<td>${client.money}</td>
						<td><a type="button" class="btn btn-warning"
							href="/sendMoneyPage?id=${client.id}&name=${client.name}&senderId=${senderId}"> Send Money</a></td>
						 
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/" class="btn btn-success">Home Page</a>
	</div>
	<%@ include file="common/footer.jspf" %>

 	
