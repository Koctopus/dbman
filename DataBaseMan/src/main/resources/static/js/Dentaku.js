function Dentaku()
{
	var a = Number(document.getElementById('Num1').value);
	var b = Number(document.getElementById('Num2').value);
	var ans = Number(document.getElementById('Answer').value);
	if (a+b==ans)
 	{
		alert("true");
		document.getElementById("result").innerText = "true";
 	}
	else
	{
		alert("false");
		document.getElementById("result").innerHTML = "false<br>答えは"+(a+b);
	}
}