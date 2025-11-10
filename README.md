# 🔔 Firebase Cloud Messaging - Mini Projet Android

Application Android permettant de recevoir et gérer des notifications push via Firebase Cloud Messaging (FCM).

## 📋 Table des matières

- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration Firebase](#configuration-firebase)
- [Structure du projet](#structure-du-projet)
- [Utilisation](#utilisation)
- [Envoi de notifications](#envoi-de-notifications)
- [Captures d'écran](#captures-décran)
- [Technologies utilisées](#technologies-utilisées)
- [Auteur](#auteur)

## 🎯 Aperçu

Ce mini-projet démontre l'implémentation de Firebase Cloud Messaging (FCM) dans une application Android pour :
- Recevoir des notifications push
- Afficher des notifications avec titre, corps et image
- Gérer les notifications en arrière-plan et au premier plan
- Personnaliser l'apparence des notifications

## ✨ Fonctionnalités

- ✅ Réception de notifications push en temps réel
- ✅ Notifications avec titre, message et image
- ✅ Gestion des notifications en arrière-plan
- ✅ Notifications interactives avec actions
- ✅ Badge de notification
- ✅ Son et vibration personnalisés
- ✅ Historique des notifications reçues
- ✅ Token FCM automatiquement généré

## 📦 Prérequis

- Android Studio Arctic Fox ou supérieur
- Compte Firebase (gratuit)
- Android SDK 21 (Android 5.0) ou supérieur
- Connexion Internet
- Appareil Android ou émulateur avec Google Play Services

## 🚀 Installation

### 1. Cloner le projet

```bash
git clone https://github.com/aymen-afamm/Notification.git
cd firebase-notifications-app
```

### 2. Ouvrir dans Android Studio

1. Ouvrez Android Studio
2. File > Open
3. Sélectionnez le dossier du projet
4. Attendez la synchronisation Gradle

### 3. Configuration Firebase

Suivez les étapes détaillées dans la section [Configuration Firebase](#configuration-firebase) ci-dessous.

## 🔥 Configuration Firebase

### Étape 1 : Créer un projet Firebase

1. Accédez à [Firebase Console](https://console.firebase.google.com/)
2. Cliquez sur **"Ajouter un projet"**
3. Entrez le nom du projet : `NotificationsApp`
4. Acceptez les conditions et créez le projet

### Étape 2 : Ajouter l'application Android

1. Dans la console Firebase, cliquez sur l'icône **Android**
2. Entrez le package name : `com.example.notificationsapp`
3. Téléchargez le fichier `google-services.json`
4. Placez-le dans : `app/google-services.json`

### Étape 3 : Ajouter les dépendances

Déjà configuré dans `build.gradle` :

```gradle
// Project level build.gradle
buildscript {
    dependencies {
        classpath 'com.google.gms:google-services:4.4.0'
    }
}

// App level build.gradle
plugins {
    id 'com.google.gms.google-services'
}

dependencies {
    implementation platform('com.google.firebase:firebase-bom:32.7.0')
    implementation 'com.google.firebase:firebase-messaging-ktx'
    implementation 'com.google.firebase:firebase-analytics-ktx'
}
```

### Étape 4 : Récupérer la clé du serveur

1. Dans Firebase Console : **Paramètres du projet** ⚙️
2. Onglet **Cloud Messaging**
3. Copiez la **Clé du serveur** (Server Key)
4. Conservez-la pour envoyer des notifications

## 📁 Structure du projet

```
app/
├── src/
│   └── main/
│       ├── java/com/example/Notification/
│       │   ├── MainActivity.kt
│       │   ├── MyFirebaseMessagingService.kt
│       │   └── NotificationHelper.kt
│       ├── res/
│       │   ├── layout/
│       │   │   └── activity_main.xml
│       │   ├── drawable/
│       │   │   └── ic_notification.xml
│       │   └── values/
│       │       └── strings.xml
│       ├── AndroidManifest.xml
│       └── google-services.json
└── build.gradle
```

## 🎮 Utilisation

### Lancer l'application

1. Connectez un appareil Android ou démarrez un émulateur
2. Cliquez sur **Run** ▶️ dans Android Studio
3. L'application s'ouvre et affiche votre **Token FCM**
4. Copiez ce token pour envoyer des notifications de test

### Recevoir des notifications

L'application reçoit automatiquement les notifications lorsque :
- Elle est en arrière-plan
- Elle est fermée
- L'écran est verrouillé
- Elle est au premier plan

## 📤 Envoi de notifications

### Méthode 1 : Console Firebase (Interface graphique)

1. Allez dans **Firebase Console** > **Cloud Messaging**
2. Cliquez sur **"Envoyer votre première notification"**
3. Remplissez :
   - **Titre** : "Nouvelle notification"
   - **Message** : "Ceci est un test"
   - **Image** (optionnel) : URL d'une image
4. Cliquez sur **"Suivant"**
5. Sélectionnez l'application
6. Planifiez ou envoyez maintenant

### Méthode 2 : API REST (Postman / cURL)

#### Avec Postman

**URL** : `https://fcm.googleapis.com/fcm/send`

**Headers** :
```
Content-Type: application/json
Authorization: key=VOTRE_CLE_SERVEUR_FIREBASE
```

**Body** (JSON) :
```json
{
  "to": "TOKEN_FCM_DE_L_APPAREIL",
  "notification": {
    "title": "Titre de la notification",
    "body": "Corps du message",
    "image": "https://example.com/image.jpg",
    "sound": "default",
    "click_action": "OPEN_ACTIVITY"
  },
  "data": {
    "type": "message",
    "id": "123",
    "timestamp": "2024-01-15T10:30:00"
  }
}
```

#### Avec cURL (Terminal)

```bash
curl -X POST https://fcm.googleapis.com/fcm/send \
  -H "Content-Type: application/json" \
  -H "Authorization: key=VOTRE_CLE_SERVEUR" \
  -d '{
    "to": "TOKEN_APPAREIL",
    "notification": {
      "title": "Hello!",
      "body": "Test de notification",
      "icon": "ic_notification"
    }
  }'
```

### Méthode 3 : Depuis un backend (Node.js)

```javascript
const admin = require('firebase-admin');

admin.initializeApp({
  credential: admin.credential.cert('serviceAccountKey.json')
});

const message = {
  notification: {
    title: 'Nouvelle notification',
    body: 'Message de test'
  },
  token: 'TOKEN_FCM_APPAREIL'
};

admin.messaging().send(message)
  .then((response) => {
    console.log('Notification envoyée:', response);
  })
  .catch((error) => {
    console.log('Erreur:', error);
  });
```


## 🛠 Technologies utilisées

- **Kotlin** - Langage de programmation
- **Firebase Cloud Messaging** - Service de notifications push
- **Firebase Analytics** - Analyse d'utilisation
- **Android SDK** - Développement Android
- **Material Design** - Interface utilisateur
- **Gradle** - Système de build

## 📊 Format de notification avancé

### Notification avec image

```json
{
  "to": "TOKEN",
  "notification": {
    "title": "Nouvelle photo",
    "body": "Découvrez cette image",
    "image": "https://picsum.photos/400/300"
  }
}
```

### Notification avec actions

```json
{
  "to": "TOKEN",
  "notification": {
    "title": "Nouveau message",
    "body": "Vous avez reçu un message"
  },
  "data": {
    "action": "open_chat",
    "user_id": "123"
  }
}
```

### Notification à tous les appareils (Topic)

```json
{
  "to": "/topics/all_users",
  "notification": {
    "title": "Annonce importante",
    "body": "Nouvelle mise à jour disponible"
  }
}
```

## 🔔 Types de notifications supportés

| Type | Description | Exemple |
|------|-------------|---------|
| Simple | Titre + message | Notification basique |
| Image | Avec image grande | Photo, bannière |
| Action | Boutons interactifs | Répondre, Ignorer |
| Son | Son personnalisé | Sonnerie custom |
| Badge | Compteur d'icône | Nombre de messages |

## ⚙️ Configuration avancée

### Personnaliser l'icône

Dans `res/drawable/ic_notification.xml` :
```xml
<vector android:width="24dp" android:height="24dp">
    <path android:fillColor="#FFD700" android:pathData="..."/>
</vector>
```

### Personnaliser le son

Placez votre fichier audio dans `res/raw/notification_sound.mp3`

### Personnaliser les couleurs

Dans `res/values/colors.xml` :
```xml
<color name="notification_color">#FFD700</color>
```

## 🐛 Dépannage

### Problème : Les notifications ne s'affichent pas

**Solutions** :
1. Vérifiez que `google-services.json` est dans `app/`
2. Vérifiez les permissions dans `AndroidManifest.xml`
3. Assurez-vous que Google Play Services est installé
4. Vérifiez que l'application n'est pas en mode "Ne pas déranger"

### Problème : Token FCM non généré

**Solutions** :
1. Vérifiez la connexion Internet
2. Redémarrez l'application
3. Effacez les données de l'application
4. Réinstallez l'application

### Problème : Erreur 401 (Unauthorized)

**Solution** :
Vérifiez que la clé serveur Firebase est correcte dans les headers.

## 📝 Checklist de déploiement

- [ ] Fichier `google-services.json` ajouté
- [ ] Clé serveur Firebase récupérée
- [ ] Permissions ajoutées dans AndroidManifest
- [ ] Service Firebase déclaré
- [ ] Application testée sur appareil réel
- [ ] Notifications testées en arrière-plan
- [ ] Documentation complétée

## 🔐 Sécurité

⚠️ **Important** :
- Ne commitez JAMAIS votre `google-services.json` sur GitHub public
- Ajoutez-le dans `.gitignore`
- Ne partagez jamais votre clé serveur Firebase
- Utilisez des variables d'environnement pour les clés sensibles

## 📚 Ressources

- [Documentation Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging)
- [Guide Android Notifications](https://developer.android.com/develop/ui/views/notifications)
- [Firebase Console](https://console.firebase.google.com/)
- [Postman Collection FCM](https://www.postman.com/firebase)

## 🤝 Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le projet
2. Créez une branche (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add AmazingFeature'`)
4. Pushez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👨‍💻 Auteur

**Votre Nom**
- GitHub: https://github.com/aymen-afamm
- Email: aymenzagnouni@gmail.com


⭐ Si ce projet vous a aidé, n'hésitez pas à lui donner une étoile sur GitHub !

