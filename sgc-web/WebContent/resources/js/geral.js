/**
 * Funcoes genericas
 */

function permiteNumeros(e) {
	
	var tecla;
		
    if(window.event) {
            tecla = e.keyCode;// capturando a tecla digitado no IE
    }
    else if(e.which) {
            tecla = e.which;// capturando a tecla digitado nos outros navegadores
    }
    else {
          return true;
    }
                   
    if ((tecla >= 48 && tecla <= 57) || tecla == 8) {
        return true;
    }
    else {
        return false;
    }

}

function impedeF5() {
	var tecla = window.event.keyCode;
	if (tecla == 116) {		
		event.keyCode = 0;
		event.returnValue = false;
	}
}

function deixaSoNumeros(origem) {
	while (origem.search(/\D/) > -1) {
		origem = origem.replace(/\D/, '');
	}
	return origem;
}

function validaCPF(obj) {
	
	cpf  = deixaSoNumeros(obj.value);
	
	if(cpf==""){
		return;
	}
	
	var numeros, digitos, soma, i, resultado, digitos_iguais;
	digitos_iguais = 1;
	if (cpf.length < 11){
		alert("CPF invalido !");
	    obj.focus();
	    setTimeout("document.getElementById('"+obj.id+"').focus();",1);
	    setTimeout("document.getElementById('"+obj.id+"').select();",1);
	    return false;
	}
	     
	for (i = 0; i < cpf.length - 1; i++)
		if (cpf.charAt(i) != cpf.charAt(i + 1)) {
			digitos_iguais = 0;
			break;
		}
	if (!digitos_iguais) {
		numeros = cpf.substring(0, 9);
		digitos = cpf.substring(9);
		soma = 0;
		for (i = 10; i > 1; i--)
			soma += numeros.charAt(10 - i) * i;
		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != digitos.charAt(0)){
			alert("CPF invalido !");
			setTimeout("document.getElementById('"+obj.id+"').focus();",1);
		    setTimeout("document.getElementById('"+obj.id+"').select();",1);
			return false;
		}
			
		numeros = cpf.substring(0, 10);
		soma = 0;
		for (i = 11; i > 1; i--)
			soma += numeros.charAt(11 - i) * i;
		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != digitos.charAt(1)){
			alert("CPF invalido !");
			setTimeout("document.getElementById('"+obj.id+"').focus();",1);
		    setTimeout("document.getElementById('"+obj.id+"').select();",1);						
			return false;
		}			
	} else{
		alert("CPF invalido !");
		setTimeout("document.getElementById('"+obj.id+"').focus();",1);
	    setTimeout("document.getElementById('"+obj.id+"').select();",1);	
		return false;
	}
		
}

// Formatar a mascara de CPF
function formataCpf(campo, teclapres)
{
	var tecla = teclapres.keyCode;
	var vr = new String(campo.value);
	vr = vr.replace(".", "");
	vr = vr.replace("/", "");
	vr = vr.replace("-", "");
	tam = vr.length + 1;
	if (tecla != 14)
	{
		if (tam == 4)
			campo.value = vr.substr(0, 3) + '.';
		if (tam == 7)
			campo.value = vr.substr(0, 3) + '.' + vr.substr(3, 6) + '.';
		if (tam == 11)
			campo.value = vr.substr(0, 3) + '.' + vr.substr(3, 3) + '.' + vr.substr(7, 3) + '-' + vr.substr(11, 2);
	}
}

// Funcao responsavel por formatar e validar o campo de data
function formatarData(campo) {

	if ((campo.value.length > 2) && (campo.value.charAt(2) != '/')) {
		temp = campo.value;
		campo.value = temp.substr(0, 2) + '/' + temp.substr(2, temp.length);
	}

	if ((campo.value.length > 5) && (campo.value.charAt(5) != '/')) {
		temp = campo.value;
		campo.value = temp.substr(0, 5) + '/' + temp.substr(5, temp.length);
	}

	return true;
}

/*-----------------------------------------------------------------------
Mascara para o campo data dd/mm/aaaa hh:mm:ss
Exemplo: <input maxlength="16" name="datahora" onKeyPress="DataHora(event, this)">
http://codigofonte.uol.com.br/codigos/mascara-para-caixas-de-texto-com-data-e-hora
-----------------------------------------------------------------------*/
function DataHora(evento, objeto) {
	var keypress = (window.event) ? event.keyCode : evento.which;
	campo = eval(objeto);
	if (campo.value == '00/00/0000 00:00:00') {
		campo.value = "";
	}

	caracteres = '0123456789';
	separacao1 = '/';
	separacao2 = ' ';
	separacao3 = ':';
	conjunto1 = 2;
	conjunto2 = 5;
	conjunto3 = 10;
	conjunto4 = 13;
	conjunto5 = 16;
	if ((caracteres.search(String.fromCharCode(keypress)) != -1)
			&& campo.value.length < (19)) {
		if (campo.value.length == conjunto1)
			campo.value = campo.value + separacao1;
		else if (campo.value.length == conjunto2)
			campo.value = campo.value + separacao1;
		else if (campo.value.length == conjunto3)
			campo.value = campo.value + separacao2;
		else if (campo.value.length == conjunto4)
			campo.value = campo.value + separacao3;
		else if (campo.value.length == conjunto5)
			campo.value = campo.value + separacao3;
	} else
		event.returnValue = false;
}

function validaData(campo){
	if (campo.value.length > 0) {

		array = campo.value.split("/");

		var dia = array[0];

		var mes = array[1] - 1;

		var ano = array[2];

		temp = new Date(ano, mes, dia);

		if ((temp.getDate() != dia) || (temp.getMonth() != mes)
				|| (temp.getFullYear() != ano)) {
			
			alert("Data invalida.");
			
			setTimeout("document.getElementById('"+campo.id+"').focus();",1);
		    setTimeout("document.getElementById('"+campo.id+"').select();",1);
		}

	}

}


//Funcao responsavel por formatar e validar o campo de hora
function formatarHora(campo) {

	if ((campo.value.length > 2) && (campo.value.charAt(2) != ':')) {
		temp = campo.value;
		
		campo.value = temp.substr(0, 2) + ':' + temp.substr(2, temp.length);
	}

	return true;
}

function validaHora(campo){
	if (campo.value.length > 0) {

		array = campo.value.split(":");

		var horas = array[0];

		var minutos = array[1];

		temp = new Date(0, 0, 0, horas, minutos, 0);

		if ((temp.getHours() != horas) || (temp.getMinutes() != minutos)) {
			
			alert("Hora invalida.");
			
			setTimeout("document.getElementById('"+campo.id+"').focus();",1);
		    setTimeout("document.getElementById('"+campo.id+"').select();",1);
		}

	}
	padrao = /\(?\d{3}\)?\d{3}-\d{4}/;
	function testInfo(x) {
	    a = x.Phone.value;
	    OK = padrao.exec(a);
	    if (!OK){
	       alert ("Este numero de telefone esta incorreto!");
	       x.Phone.focus();
	    } else { 
	       alert ("Obrigado, seu numero de telefone eh " + OK[0]);
	    }
	}

}

function Trim(str){return str.replace(/^\s+|\s+$/g,"");}

function toJavascriptArray(string) {
	if (string.length > 0) {
		var array = new Array();
		array = temp.split(',');
		return array;
	}
	return new Array();
}