import MenuBarWithoutRepo from "./MenuBarWithoutRepo.jsx";
export default function CreateNewCommitWithoutRepo({userPassword,userName}){
    return(
        <div>
             <h3>Create New Commit</h3>
           <MenuBarWithoutRepo
           userPassword={userPassword}
           userName={userName}
           />
        </div>
    );
}
//need to show the list of all the repo 
//then need to suplly the data 