import {ServerResponse} from './Name.js';
import Repo from './Repo.jsx';
export default function Header(){
    return(
        
        <main>
            <section id="core-concepts" className='scrollable-container'>
            <h2>My-Repo</h2>
            <ul id="nameList">
                {ServerResponse.map((conceptItem) => (
                   <Repo key={conceptItem.index} name={conceptItem.name}/> 
                ))}
            
            </ul>
            </section> 
        </main>
        
    );
}