var express = require('express');
var morgan = require('morgan'); // Charge le middleware de logging
var logger = require('log4js').getLogger('Server');
var bodyParser = require('body-parser');
var XMLHttpRequest = require('xhr2');
var app = express();

// config
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

app.use(bodyParser.urlencoded({ extended: false }));
app.use(morgan('combined')); // Active le middleware de logging

app.use(express.static(__dirname + '/public')); // Indique que le dossier /public contient des fichiers statiques (middleware chargé de base)
var t;

logger.info('server start');

// Route
app.get('/', function(req, res){
    res.redirect('/login');
});

app.get('/scantest', function(req, res)
{
	if (t == null) 
	{
		var adr = "http://localhost:8080/badgeuse/scan";
		var http = new XMLHttpRequest();
		
		http.open("GET", adr, true);
		http.onreadystatechange = function()
		{
			if(http.readyState==4)
			{
				if (http.status == 200) 
				{
					//t=JSON.parse(http.responseText).test;
					t = http.responseText;
					logger.info("t : ", t);
				}
				else
				{
					logger.info('Status Page : ', http.status);
					t = "erreur chargement de la page";
				}
			}
		}
		http.send(null);
	}
	
	logger.info('test : ', t);
	if (t != null) 
	{
		res.render('testscan', {test:t});
	}
    else
    {
    	res.redirect('/scantest');
    }
});

app.get('/login', function(req, res){
    res.render('login');
});

app.get('/badgeetudiant', function (req, res){
	res.render('badgeetudiant');

});

app.get('/loginprof', function (req,res){
	res.render('loginprof');
});

app.listen(1313);
