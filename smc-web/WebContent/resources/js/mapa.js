/**
 * Funcoes do mapa
 */
var x = document.getElementById("demo");
var map;
var infowindow;
var latlon;
var bounds;

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
}

function showPositionJSON(json) {
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
		var idUsuario = parsedJSON[i].idUsuario;
		var idIncidente = parsedJSON[i].idIncidente;
		var title = parsedJSON[i].detalhe;
		var status = "active";

		bounds.extend(latLng);
		addMarker(latLng, i, icone, (idUsuario != "" ? idUsuario : idIncidente), title, status);
	}

	function addMarker(location, i, icone, idUsuario, title, active) {
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
			}
		})(marker, i));

		// Remover informacao do marcador ao retirar o mouse de cima
		google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
			return function() {
				infowindow.close(map, marker);
			}
		})(marker, i));

		// Evento de clicar
		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				alert('Nao faz nada ainda!');
				// TODO enviar para proxima pagina com o idUsuario
			}
		})(marker, i));
	}

	map.fitBounds(bounds); //centralizar baseado nos marcadores
    map.panToBounds(bounds); //centralizar baseado nos marcadores

	/*
	 * var total = 5;
	 * for (i = 0; i &lt; '#{usuarioMB.total}'; i++) { marker = new
	 * google.maps.Marker({ position: new google.maps.LatLng(locations[i][1],
	 * locations[i][2]), icon:'#{usuarioMB.usuarioSessao.grupo.icone}',
	 * //title:"Você está Aqui!", map: map
	 * //,animation:google.maps.Animation.BOUNCE // Animacao });
	 *
	 * google.maps.event.addListener(marker, 'click', (function(marker, i) {
	 * return function() { infowindow.setContent(locations[i][0]);
	 * infowindow.open(map, marker); } })(marker, i)); }
	 */

	// Tipos de overlays
	// Marker - Single locations on a map. Markers can also display custom icon
	// images
	// Polyline - Series of straight lines on a map
	// Polygon - Series of straight lines on a map, and the shape is "closed"
	// Circle and Rectangle
	// Info Windows - Displays content within a popup balloon on top of a map
	// Custom overlays
}

function usuariosPorUltimaLocalizacaoNoIntervalo(json) {
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
		var idUsuario = parsedJSON[i].idUsuario;
		var title = parsedJSON[i].detalhe;
		var status = "active";

		bounds.extend(latLng);
		addMarkerPorUltimaLocalizacao(latLng, i, icone, idUsuario, title, status);
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
		}
	})(marker, i));

	// Remover informacao do marcador ao retirar o mouse de cima
	google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
		return function() {
			infowindow.close(map, marker);
		}
	})(marker, i));

	// Evento de clicar
	google.maps.event.addListener(marker, 'click', (function(marker, i) {
		return function() {
			alert('Nao faz nada ainda!');
			// TODO enviar para proxima pagina com o idUsuario
		}
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