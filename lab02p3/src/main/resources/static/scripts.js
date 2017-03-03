$(document).ready(function()   {
	
	$.getJSON("customer", function(theList) {
	
		for (var i = 0; i < theList.length; i++)  {
			var row = "<tr>" 
					+ "<td>" + theList[i].customerID + "</td>"
					+ "<td>" + theList[i].name  + "</td>" 
					+ "<td>" + theList[i].phone + "</td>" 
					+ "<td>" + theList[i].email + "</td>" 
					+ "</tr>";
			$('#custlist').append(row);
		}
	});
}
);