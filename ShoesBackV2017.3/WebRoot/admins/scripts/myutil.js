/**
 * 
 */
function delById(aid){
		if(confirm('ȷ��ɾ����')==true){
		   location="/b2c_shoes/admins!delete.action?aid="+aid;
		}
}

function SetChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
		f.elements[i].checked=true;
		}
	}
}
function SetResverseChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
			if(f.elements[i].checked==true){
				f.elements[i].checked=false;
			}else{
				f.elements[i].checked=true;
			}
		}
	}
}
function SetUnChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
		f.elements[i].checked=false;
		}
	}
}

function checkForm(){
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name=="chk_aid") {
			if(f.elements[i].checked==true){
				if(confirm("ȷ������ɾ����Щ������")){
					return true;
				}else{
					return false;
				}
			}
		}
	}
	alert("����ѡ��һ�����ݡ���");
	return false;
}
function checkForm1(){
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name=="chk_aid") {
			if(f.elements[i].checked==true){
				if(confirm("ȷ������ɾ����Щ�����𣿣�ע�⣺�������йص����ݽ�������ɾ��!��")){
					return true;
				}else{
					return false;
				}
			}
		}
	}
	alert("����ѡ��һ�����ݡ���");
	return false;
}