function search(event)
{
	const droparea = document.getElementById("circuit_image");
	droparea.style.background = "#E0E0E0";
	event.preventDefault();
}
function droping(event)
{
	event.preventDefault();
	const file = event.dataTransfer.files[0];
	const reader = new FileReader();
	reader.onload = function(){
		const thumb = document.getElementById('thumb');
		thumb.src = reader.result;
	};
	reader.readAsDataURL(file);
}
function formula_register()
{
	let box=document.getElementById("formula_box");
	if(box.className!=7)
	{
		const text=document.getElementById("formula_text_"+box.className);
		let formula=document.getElementById("formula");
		text.innerHTML = "<div class='textarea'><input type='text' id='formula_"+box.className+"' value='"+formula.value+"' readonly='readonly'></div><input type='button' id='formula_delete_"+box.className+"' value='-' onclick='formula_delete(event);'>";
		let num=parseInt(box.className)+parseInt(1);
		box.className=num;
		let div = document.createElement('div');
		if(box.className!=7)
		{
			div.id = ("formula_text_"+String(num));
			div.className = "textbox";
			div.innerHTML="<div class='textarea'><input type='text' id='formula'></div><input type='button' id='formula_go_btn' value='+' onclick='formula_register();'>";
		}
		else
		{
			div.id=""
			div.className="textbox"
			div.innerHTML="これ以上式を入力できません";
		}
		box.appendChild(div);
	}
	
}
function formula_delete(event)
{
	let box=document.getElementById("formula_box");
	num=parseInt(event.target.id.replace("formula_delete_",""));
	const text=document.getElementById("formula_text_"+num);
	box.removeChild(text);
	let max=document.getElementById("max");
	if(max.length)
	{
		box.removeChild(max);
		num=num-1;
	}
	for(let i=num+1;i<=box.className;i++)
	{
		document.getElementById("formula_text_"+i).id=("formula_text_"+String(i-1));
		if(i!=box.className)
		{
			document.getElementById("formula_"+i).id=("formula_"+String(i-1));
			document.getElementById("formula_delete_"+i).id=("formula_delete_"+String(i-1));
		}
	}
	box.className=parseInt(box.className)-parseInt(1);
}
function save(event)
{
	const num=parseInt(document.getElementById("formula_box").className);
	const img=document.getElementById("thumb").src;
	let formula = new Array(num+parseInt(1));
	let data;
	let i;
	data=img;
	for(i=0;i<num;i++)
	{
		formula[i]=document.getElementById("formula_text_"+i).innerHTML;
		data=data+","+formula[i];
	}
	alert(data);
}
function openload(event)
{
	let data=document.getElementById("test").value.split(',');
	let img=document.getElementById("thumb");
	let i;
	let formula="";
	img.src=data[0];
	for(i=0;i<data.length-1;i++)
	{
		formula=formula+"<div id='formula_text_'"+i+"class='textbox'>";
		formula=formula+data[i+1]+"</div>";
	}
	formula=formula+"<div id='formula_text_'"+data.length+"class='textbox'>";
	formula=formula+"<input type='text' id='formula'><input type='button' id='formula_go_btn' value='Go' onclick='formula_register();'></div>";
	let box=document.getElementById("formula_box");
	box.innerHTML=formula;
}
//$("#edit_form").submit()
