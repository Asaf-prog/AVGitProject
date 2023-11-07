import Commit from "./Commit.jsx";
import  { useState } from 'react';
export default function Repo({name,index}){
    const [showNewComponent, setShowNewComponent] = useState(false);
    
    const handleRepoClick = () => {
        // Toggle the showNewComponent state to true when the Repo is clicked
        console.log('print');
        setShowNewComponent(true);
      };

    return (
        <li onClick={handleRepoClick}>
            
            <h3>
                {!name ? 'null' : name }
                {index && <span>Index: {index}</span>}                
    
            </h3>
            <div>
                {showNewComponent ?<Commit name={name}/>: null} 
            </div>


        </li>
    );
}