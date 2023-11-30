import Login from "./components/Login.jsx";
import Body from "./components/showDetailsComponents/Body.jsx";
import ProjectSidebar from "./components/ProjectsSidebar.jsx";
import CreateNewCommit from "./components/createNewRepo/CreateNewCommit.jsx";
import { useState } from 'react';

function App(){
    const [showLoginComponent, setShowLoginComponent] = useState(true);
    const [showHeaderComponent, setShowHeaderComponent] = useState(false);
    const [showBodyComponent, setShowBodyComponent] = useState(false);
    const [showCreateNewCommit, setCreateNewCommit] = useState(false);
    
    const[userName, setUserName] = useState({
            user:'hello',
    });

    const[userPassword, setUserPassword] = useState({
        password:'',
    });

    function handleChangeLogin(){
        setShowLoginComponent(false);
        setShowHeaderComponent(true);
    }

    function setIsRegister() {
        setShowLoginComponent(false);
        setShowHeaderComponent(true);
    }
    function handleBodyComponent() {
        setShowBodyComponent(true);
        setCreateNewCommit(false);
    }

    function handleCreateNewCommit() {
        setCreateNewCommit(true);
        setCreateNewCommit(true);
        setShowBodyComponent(false);
    }


   
    return(
    <main >
        {showLoginComponent ? <Login onChange={handleChangeLogin} 
        name = {userName}
        userPassword = {userPassword}
          setIsRegister={setIsRegister}/> : null}
          {!showLoginComponent ?<h3>Welcome {userName.user}</h3>  : null}
         
         {showHeaderComponent ? <ProjectSidebar handleBodyComponent={handleBodyComponent}
         handleCreateNewCommit={handleCreateNewCommit}
           />: null} 
         
         {showBodyComponent ? <Body  
         userName={userName.user}
         userPassword={userPassword} />: null} 
         
         {showCreateNewCommit ? <CreateNewCommit  />: null} 

    </main>
);
}

export default App;