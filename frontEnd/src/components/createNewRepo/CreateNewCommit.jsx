 import MenuBar from "./MenuBar.jsx";
 export default function CreateNewCommit({userPassword}) {
    return(
        <div >
            <h3>Create New Repository</h3>
           <MenuBar
           userPassword={userPassword}
           />
        </div>
    );
}