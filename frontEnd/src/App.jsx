import Login from "./components/Login.jsx";
import Body from "./components/Body.jsx";
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

    function setIsRegister() {
        setShowLoginComponent(false);
        setShowHeaderComponent(true);
    }

    return(
    <div>
        {showLoginComponent ? <Login onChange={handleChangeLogin} 
        name ={userName}
          setIsRegister={setIsRegister}/> : null}
        {!showLoginComponent ? `Welcome, ${userName.user}` : null}
        {showHeaderComponent ? <Body />: null}
    </div>
);
}

export default App;