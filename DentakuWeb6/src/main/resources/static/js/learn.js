function formula()
{
	let data=",R*I,E/I,E/R";
	let formula=data.split(',');
	let box=document.getElementById("formula_box");
	let element=document.getElementById("element_box");
	let ht="";
	let i;
	let j;
	for (i = 1; i < formula.length; i++)
	{
		ht=ht+"<div id='formula_text_"+i+"'><div id='formula_"+i+"'>"+formula[i]+"</div></div><input type='button' id='formula_go_btn_"+i+"' value='Go' onclick='Calculation()'>"
		let variable=formula[i].split(/[-\/\*\+]/);
		variable.filter(function (x, k, self) {
			  return self.indexOf(x) === k;
			});
		for(j=0;j<variable.length;j++)
		{
			if(document.getElementById(variable[j])==null)
			{
				let ele=document.createElement('div')
				ele.innerHTML=variable[j]+"<input type='text' id='"+variable[j]+"'>";
				element.appendChild(ele);
			}
		}
	}
	box.innerHTML = ht;
}
function Calculation()
{
	let formula=document.getElementById("formula_1").innerHTML;
	let result=formula.split(/[-\/\*\+]/);
	for (let i = 0; i < result.length; i++)
	{
			let val=document.getElementById(result[i]);
			formula=formula.replace(result[i].replace(/[()]+/ ,""),val.value);
	}
	alert(eval(formula));
}