import Login from "./components/Login.jsx";
import Body from "./components/showDetailsComponents/Body.jsx";
import ProjectSidebar from "./components/ProjectsSidebar.jsx";
import CreateNewCommit from "./components/createNewRepo/CreateNewCommit.jsx";
import CreateNewCommitWithoutRepo from "./components/createNewCommit/CreateNewCommitWithoutRepo.jsx";
import { useState } from 'react';

function App(){
    
    const [showLoginComponent, setShowLoginComponent] = useState(true);
    const [showHeaderComponent, setShowHeaderComponent] = useState(false);
    const [showBodyComponent, setShowBodyComponent] = useState(false);
    const [showCreateNewRepository, setCreateNewRepository] = useState(false);
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
        setCreateNewRepository(false);
        setCreateNewCommit(false);
    }

    function handleCreateNewRepository() {
        setCreateNewRepository(true);
        setCreateNewCommit(false);
        setShowBodyComponent(false);
    }


    function handleCreateNewCommit() {
        setCreateNewCommit(true);
        setCreateNewRepository(false);
        setShowBodyComponent(false);
    }
   
    return(
    <main >
        {showLoginComponent ? <Login onChange={handleChangeLogin} 
        name = {userName}
        userPassword = {userPassword}
          setIsRegister={setIsRegister}/> : null}
          {!showLoginComponent ?<h3>Welcome {userName.user}</h3>  : null}
         
         {showHeaderComponent ? <ProjectSidebar 
         handleBodyComponent={handleBodyComponent}
         handleCreateNewRepository={handleCreateNewRepository}
         handleCreateNewCommit={handleCreateNewCommit}
           />: null} 
         
         {showBodyComponent ? <Body  
         userName={userName.user}
         userPassword={userPassword} />: null} 
         
         {showCreateNewRepository ? <CreateNewCommit 
         userPassword={userPassword}  />: null} 
         
         {showCreateNewCommit ? <CreateNewCommitWithoutRepo 
         userPassword={userPassword} 
         userName={userName.user} />: null} 

    </main>
);
}

export default App;