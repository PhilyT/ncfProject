// configuration du fichier de config passeport afin de permettre l'identification des professeurs
// le fichier ce situ sous : config/passeport.js

// prés requis 
 var LocalStrategy = require ('passport-local').Strategy;
 var mysql = require('mysql');

 // donnée de connection à la base de donnée - A remplir

 module.exports = function(passport) {

 	//===================================================
 	//==== Configuration des sessions ===================
 	//===================================================

 	// sérialisation et désérialisation des sessions utilisateurs
 	// sérialisation 
 	passport.serializeUser(function(user,done)
 		{
 			done(null, user.id);
 		});
 	//déserialisation
 	passport.deserializeUser(function(id,done)
 		{
 			connection.query("SELECT * FROM user WHERE id ="+id,function(err,rows)
 			{
 				done(err,rows[0]);
 			});
 		});

 	//===================================================
 	//==== Configuration connexion ======================
 	//===================================================

 	passport.use('local-signup', new LocalStrategy
 		(
 			{
 				usernameField:'email',
 				passwordField: 'password',
 				passReqToCallback : true
 			},
 			function (req, email, password, done) 
 			{
 				connection.query("SELECT * FROM users WHERE email='"+email+"'", function(err,rows)
 				{
 					console.log(rows);
 					console.log("above row object");
 					if (err) {
 						return done(err)
 					}
 					if (rows.lenght) {
 						return done(null, false, req.flash('signupMessage','Cette email existe déja.'));
 					}
 					else {
 						
 					}
 				})
 			}
 		)
 	)

 }

