/**
 * Funcoes do mapa
 */
var x = document.getElementById("demo");
var map;
var infowindow;
var latlon;
var bounds;
var paginaCaminhoUsuario = "caminho_usuario.xhtml";

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
	} else {
		alert("Geolocalização não é suportada nesse browser.");
	}
}

function showPosition(position) {
	lat = position.coords.latitude;
	lon = position.coords.longitude;

	if (lat == null) {
		lat = 0;
	}
	if (lon == null) {
		lon = 0;
	}
	document.getElementById('mapform:latitude').innerHTML = lat;
	document.getElementById('mapform:longitude').innerHTML = lon;

	latlon = new google.maps.LatLng(lat, lon);
	
	// Tipos de overlays
	// Marker - Single locations on a map. Markers can also display custom icon
	// images
	// Polyline - Series of straight lines on a map
	// Polygon - Series of straight lines on a map, and the shape is "closed"
	// Circle and Rectangle
	// Info Windows - Displays content within a popup balloon on top of a map
	// Custom overlays
}

function usuariosPorLocalizacao(json) {
	// Caso venha vazio nao faz nada
	if (json == null || json == '') {
		return;
	}

	var myOptions = {
		//center: latlon,  //map.setCenter(new google.maps.LatLng(parsedJSON[i].latitude, parsedJSON[i].longitude));
		//zoom: 14,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		// Tipos de MapTypeId
		// ROADMAP (normal, default 2D map)
		// SATELLITE (photographic map)
		// HYBRID (photographic map + roads and city names)
		// TERRAIN (map with mountains, rivers, etc.)
		mapTypeControl: false,
		navigationControlOptions: {
			style: google.maps.NavigationControlStyle.SMALL
		}
	};

	// Create a Map Object
	map = new google.maps.Map(document.getElementById("mapholder"), myOptions);
	infowindow = new google.maps.InfoWindow();
	bounds = new google.maps.LatLngBounds(); //centralizar automaticamente o mapa

	var parsedJSON = $.parseJSON(json);

	for (var i in parsedJSON) {
		var latLng = new google.maps.LatLng(parsedJSON[i].latitude, parsedJSON[i].longitude);
		var icone = parsedJSON[i].icone;
		var id = parsedJSON[i].idUsuario;
		var idGrupo = parsedJSON[i].idGrupo;
		var title = parsedJSON[i].detalhe;
		var status = "active";

		bounds.extend(latLng);
		if (id == null || id == "") {
			id = parsedJSON[i].idIncidente;
		}
		if (idGrupo == null || idGrupo == "") {
			addMarkerPorUltimaLocalizacao(latLng, i, icone, id, title, status);
		} else {
			addMarkerPorUltimaLocalizacao(latLng, i, icone, id, idGrupo, title, status);
		}
		
	}

	map.fitBounds(bounds); //centralizar baseado nos marcadores
    map.panToBounds(bounds); //centralizar baseado nos marcadores
}

function addMarkerPorUltimaLocalizacao(location, i, icone, idUsuario, title, active) {
	var marker = new google.maps.Marker({
		position: location,
		icon: icone,
		// title:"Você está Aqui!",
        status: active,
		map: map
	});

	// Adicionar informacao no marcador ao passar o mouse em cima
	google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
		return function() {
			infowindow.setContent(title);
			infowindow.open(map, marker);
		};
	})(marker, i));

	// Remover informacao do marcador ao retirar o mouse de cima
	google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
		return function() {
			infowindow.close(map, marker);
		};
	})(marker, i));

	// Evento de clicar
	google.maps.event.addListener(marker, 'click', (function(marker, i) {
		return function() {
			if (confirm("Deseja rastrear '" + title + "' ?")) {
				window.location = paginaCaminhoUsuario + "?idUsuario=" + idUsuario;
			}
		};
	})(marker, i));
}

function addMarkerPorUltimaLocalizacao(location, i, icone, idUsuario, idGrupo, title, active) {
	var marker = new google.maps.Marker({
		position: location,
		icon: icone,
		// title:"Você está Aqui!",
        status: active,
		map: map,
		grupo: idGrupo
	});

	// Adicionar informacao no marcador ao passar o mouse em cima
	google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
		return function() {
			infowindow.setContent(title);
			infowindow.open(map, marker);
		};
	})(marker, i));

	// Remover informacao do marcador ao retirar o mouse de cima
	google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
		return function() {
			infowindow.close(map, marker);
		};
	})(marker, i));

	// Evento de clicar
	google.maps.event.addListener(marker, 'click', (function(marker, i) {
		return function() {
			alert('Meu grupo e: '+marker.grupo);
			if (confirm("Deseja rastrear '" + title + "' ?")) {
				window.location = paginaCaminhoUsuario + "?idUsuario=" + idUsuario;
			}
		};
	})(marker, i));
}

function caminhoUsuario(json) {
	// Caso venha vazio nao faz nada
	if (json == null || json == '') {
		return;
	}

	var directionsService = new google.maps.DirectionsService({
		//avoidHighways: true
	});

	var directionsDisplay = new google.maps.DirectionsRenderer({
		suppressMarkers: true,
		suppressInfoWindows: true
	});

	var myOptions = {
		zoom: 5,
		center: latlon,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};

	// Create a Map Object
	map = new google.maps.Map(document.getElementById("mapholder"), myOptions);
	infowindow = new google.maps.InfoWindow();

	// Relacionamos o directionsDisplay com o mapa desejado
	directionsDisplay.setMap(map);

	var parsedJSON = $.parseJSON(json);

	var origin, destination, usuario;
	var waypoints = [];

	for (var i in parsedJSON) {
		var latLng = new google.maps.LatLng(parsedJSON[i].latitude, parsedJSON[i].longitude);
		var icone = parsedJSON[i].icone;
		var idUsuario = parsedJSON[i].idUsuario;
		var title = parsedJSON[i].detalhe;
		var status = "active";

		addMarkerCaminhoUsuario(latLng, i, icone, idUsuario, title, status);

		// Salvar os dados para criar a rota
		if (i == 0) {
			destination = latLng;
			usuario = title;
		}
		waypoints.push({
			location: latLng
		});
		origin = latLng;
	}

	//Apos o for, remover o ultimo elemento do array, pois ele e o ponto de origem.
	waypoints.pop();

	var request = {
		origin: origin,
		destination: destination,
		waypoints: waypoints,
		//travelMode: google.maps.TravelMode.DRIVING // indica rotas de trânsito padrão usando a rede rodoviária.
		travelMode: google.maps.TravelMode.WALKING // solicita rotas a pé por faixas de pedestre e calçadas.
	};

	directionsService.route(request, function(result, status) {
		if (status == google.maps.DirectionsStatus.OK) { // Se deu tudo certo
			directionsDisplay.setDirections(result); // Renderizamos no mapa o resultado
		} else {
			alert("Problema ao carregar a rota, status: "+status);
		}
	});
}

function addMarkerCaminhoUsuario(location, i, icone, idUsuario, title, active) {
	var marker = new google.maps.Marker({
		position: location,
		draggable: true,
		icon: icone,
		// title:"Você está Aqui!",
        status: active,
		map: map
	});

	// Adicionar informacao no marcador ao passar o mouse em cima
	google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
		return function() {
			infowindow.setContent(title);
			infowindow.open(map, marker);
		};
	})(marker, i));

	// Remover informacao do marcador ao retirar o mouse de cima
	google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
		return function() {
			infowindow.close(map, marker);
		};
	})(marker, i));

	// Evento de clicar
	google.maps.event.addListener(marker, 'click', (function(marker, i) {
		return function() {
			//alert("Cliquei");
			//infowindow.setContent(usuario);
			//infowindow.open(map, marker);
		};
	})(marker, i));
}

function showError(error) {
	switch (error.code) {
		case error.PERMISSION_DENIED:
			x.innerHTML = "Usuário rejeitou a solicitação de Geolocalização.";
			break;
		case error.POSITION_UNAVAILABLE:
			x.innerHTML = "Localização indisponível.";
			break;
		case error.TIMEOUT:
			x.innerHTML = "O tempo da requisição expirou.";
			break;
		case error.UNKNOWN_ERROR:
			x.innerHTML = "Algum erro desconhecido aconteceu.";
			break;
	}
}