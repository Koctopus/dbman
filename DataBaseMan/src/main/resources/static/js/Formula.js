function add()
{
	let str=document.getElementById("Formula").value;
	let result=str.split(/[-\/\*\+]/);
	let formula="";
	if(Formula!="")
	{
		for (let i = 0; i < result.length; i++)
		{
			formula=formula+result[i].replace(/[()]+/ ,"")+'<br><input type="text"id="variable['+i+']"><br>';
		}
		formula=formula+'<input type="button" value="計算" onclick="Calculation();">';
		let F_div = document.getElementById("F_div");
		F_div.innerHTML = formula;
	}
}
function Calculation()
{
	let formula=document.getElementById("Formula").value;
	let result=formula.split(/[-\/\*\+]/);
	for (let i = 0; i < result.length; i++)
	{
			let val=document.getElementById("variable["+i+"]");
			formula=formula.replace(result[i].replace(/[()]+/ ,""),val.value);
	}
	let res = document.getElementById("result");
	res.innerHTML = eval(formula);
}
