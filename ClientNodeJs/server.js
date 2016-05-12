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
app.get('/presenceC', function(req, res){
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
						/*var c = new Object[t.Cours.length];
						for(var i = 0; i<t.Cours.length; i++)
						{
							c[i].libelle = t.Cours[i].libelle;
						}*/
						res.render('presenceC', {cours:t.Cours});
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
app.post('/presenceC', function(req, res){
	var t;
	logger.info("Date :", req.body.date);
	var adr = "http://localhost:8080/get_presence?idc="+req.body.cours+"&date="+req.body.date;
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
				logger.info("presences : ", t.Presences);

				if (t.etat != 'success') 
				{
					res.redirect('/presenceC');
				}
				else
				{
					res.render('listePresenceC', {presence:t.Presences});
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
app.post('/listePresenceC', function(req, res){
	var t;
	logger.info('id du cours', req.body.cours);
	var adr = "http://localhost:8080/add_presence?idc="+req.body.cours+"&date="+req.body.date+"&ide="+req.body.etudiant;
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
				if (t.etat != 'success') 
				{
					res.redirect('/presenceC');
				}
				else
				{
					res.render('listePresenceC', {presence:t.Presences});
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
app.get('/ajoutE', function(req, res){
	if (admin != null) 
	{
		res.render('ajoutE', {etat:""});
	}
	else
	{
		res.redirect('/loginprof');
	}
});
app.post('/ajoutE', function (req,res){
	var t;
	var adr = "http://localhost:8080/add_etud?nom="+req.body.nomE+"&prenom="+req.body.prenomE;
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
				if (t.etat != 'success') 
				{
					res.render('ajoutE', {etat:t.etat});
				}
				else
				{
					res.redirect('/listeE');
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

app.get('/modifE', function(req, res){
	if (admin != null)
	{
		res.render('modifE');
	}
	else
	{
		res.redirect('/loginprof');
	}
});

app.get('/suppE', function(req, res){
	if (admin != null)
	{
		res.render('suppE');
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
						/*var c = new Object[t.Cours.length];
						for(var i = 0; i<t.Cours.length; i++)
						{
							c[i].libelle = t.Cours[i].libelle;
						}*/
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
app.get('/ajoutC', function(req, res){
	if (admin != null)
	{
		res.render('ajoutC');
	}
	else
	{
		res.redirect('/loginprof');
	}
});
app.post('/ajoutC', function (req,res){
	var t;
	logger.info('time:', req.body.heureDeb);
	var adr = "http://localhost:8080/add_cour?libelle="+req.body.libelleC+"&dateDebut="+req.body.heureDeb+"&dateFin="+req.body.heureFin;
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
				if (t.etat != 'success') 
				{
					res.render('ajoutC', {etat:t.etat});
				}
				else
				{
					res.redirect('/listeC');
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

app.get('/modifC', function(req, res){
	if (admin != null)
	{
		res.render('modifC');
	}
	else
	{
		res.redirect('/loginprof');
	}
});
app.get('/suppC', function(req, res){
	if (admin != null)
	{
		res.render('suppC');
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