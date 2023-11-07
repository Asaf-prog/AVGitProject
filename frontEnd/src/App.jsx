import Login from "./components/Login.jsx";
import Header from './components/Header';
import { useState } from 'react';
function App(){
    const [showLoginComponent, setShowLoginComponent] = useState(true);
    const [showHeaderComponent, setShowHeaderComponent] = useState(false);
    
    const[userName, setUserName] = useState({
            user:'hello',
    });
    
    function handleChangeLogin(){
        setShowLoginComponent(false);
        setShowHeaderComponent(true);

    }

    function handleUserName(){
        setUserName({
            user: newName,
          });
    }

    return(
    <div>
        {showLoginComponent ? <Login onChange={handleChangeLogin} name ={userName} 
        userNameFunc={handleUserName}/> : null}
        {!showLoginComponent ? `Welcome, ${userName.user}` : null}
        {showHeaderComponent ? <Header/>: null}
    </div>
);
}

export default App;