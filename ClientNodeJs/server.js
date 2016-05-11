//package
var express = require('express');
var morgan = require('morgan'); // Charge le middleware de logging
var logger = require('log4js').getLogger('Server');
var bodyParser = require('body-parser');
var XMLHttpRequest = require('xhr2');
var app = express();

// config
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');
var admin;
app.use(bodyParser.urlencoded({ extended: false }));
app.use(morgan('combined')); // Active le middleware de logging
app.use(express.static(__dirname + '/public')); // Indique que le dossier /public contient des fichiers statiques (middleware chargé de base)

// Route
app.get('/', function(req, res){
    res.redirect('/login');
});

app.get('/login', function(req, res){
    res.render('login');
});

app.get('/badgeetudiant', function (req, res)
{
	res.render('badgeetudiant', {etat:""});
});
app.get('/admin', function(req, res){
	if (admin != null) 
	{
		res.render('admin');
	}
	else
	{
		res.redirect('/loginprof');
	}  
});
app.get('/gestionE', function(req, res){
	if (admin != null) 
	{
		res.render('gestionE');
	}
	else
	{
		res.redirect('/loginprof');
	}
});
app.get('/ajoutE', function(req, res){
	if (admin != null) 
	{
		res.render('ajoutE');
	}
	else
	{
		res.redirect('/loginprof');
	}
});
app.get('/gestionC', function(req, res){
	if (admin != null) 
	{
		res.render('gestionC');
	}
	else
	{
		res.redirect('/loginprof');
	}
});

app.get("/logout", function(req, res)
{
	admin = null;
	res.redirect('/login');
});

app.get("/listeC", function(req, res)
{
	if (admin != null) 
	{
		var t;
		var adr = "http://localhost:8080/cours";
		var http = new XMLHttpRequest();
			
		http.open("GET", adr, true);
		http.onreadystatechange = function()
		{
			if(http.readyState==4)
			{
				if (http.status == 200) 
				{
					t=JSON.parse(http.responseText);
					//t = http.responseText;
					logger.info("t : ", t);
					logger.info("etat : ", t.etat);
					logger.info("Cours : ", t.Cours);
					logger.info("libelle 1 : ", t.Cours[0].libelle);
					if (t.etat == "success") 
					{
						res.render('listeC', {cours:t.Cours});
					}
					else
					{
						logger.info("erreur d'obtention de la liste des cours");
					}
				}
				else
				{
					logger.info('Status Page : ', http.status);
					logger.info("erreur accès au service rest");
				}
			}
		}
		http.send(null);
	}
	else
	{
		res.redirect('/loginprof');
	}
});

app.get("/listeE", function(req, res)
{
	if (admin != null) 
	{
		var t;
		var adr = "http://localhost:8080/etudiants";
		var http = new XMLHttpRequest();
			
		http.open("GET", adr, true);
		http.onreadystatechange = function()
		{
			if(http.readyState==4)
			{
				if (http.status == 200) 
				{
					t=JSON.parse(http.responseText);
					//t = http.responseText;
					logger.info("t : ", t);
					logger.info("etat : ", t.etat);
					logger.info("Etudiants : ", t.Etudiants);
					logger.info("prenom 1 : ", t.Etudiants[0].prenom);
					if (t.etat == "success") 
					{
						res.render('listeE', {etudiants:t.Etudiants});
					}
					else
					{
						logger.info("erreur d'obtention de la liste des etudiants");
					}
				}
				else
				{
					logger.info('Status Page : ', http.status);
					logger.info("erreur accès au service rest");
				}
			}
		}
		http.send(null);
	}
	else
	{
		res.redirect('/loginprof');
	}
});

app.get('/iut.png', function(req, res){
    res.sendFile('img/iut.png', { root:__dirname });
});
app.get('/unice.png', function(req, res){
    res.sendFile('img/unice.png', { root: __dirname });
});
app.get('/badgeuse.png', function(req, res){
    res.sendFile('img/badgeuse.png', { root: __dirname });
});

app.post('/badgeetudiant', function (req, res)
{
	var t;
	var adr = "http://localhost:8080/scan";
	var http = new XMLHttpRequest();
		
	http.open("GET", adr, true);
	http.onreadystatechange = function()
	{
		if(http.readyState==4)
		{
			if (http.status == 200) 
			{
				t=JSON.parse(http.responseText);
				//t = http.responseText;
				logger.info("t : ", t);
				logger.info("etat : ", t.etat);
				logger.info("user : ", t.user);
				logger.info("prenom : ", JSON.parse(t.user).prenom);
				res.render('badgeetudiant', {etat:t.etat, prenom:JSON.parse(t.user).prenom, nom:JSON.parse(t.user).nom});
			}
			else
			{
				logger.info('Status Page : ', http.status);
				logger.info("erreur accès au service rest");
			}
		}
	}
	http.send(null);
});

app.get('/loginprof', function (req,res){
	if (admin != null) 
	{
		res.redirect('/admin');
	}
	else
	{
		res.render('loginprof', {etat:""});
	}
});

app.post('/loginprof', function (req,res){
	var t;
	logger.info("password : ", req.body.password);
	var adr = "http://localhost:8080/co_admin?email="+req.body.email+"&mdp="+req.body.password;
	var http = new XMLHttpRequest();
	http.open("GET", adr, true);
	http.onreadystatechange = function()
	{
		if(http.readyState==4)
		{
			if (http.status == 200) 
			{
				t=JSON.parse(http.responseText);
				//t = http.responseText;
				logger.info("t : ", t);
				logger.info("etat : ", t.etat);
				logger.info("user : ", t.user);
				if (t.etat != 'success') 
				{
					res.render('loginprof', {etat:t.etat});
				}
				else
				{
					admin = JSON.parse(t.user);
					res.redirect('/admin');
				}
			}
			else
			{
				logger.info('Status Page : ', http.status);
				logger.info("erreur accès au service rest");
			}
		}
	}
	http.send(null);
});

app.listen(1414);
logger.info('server start');