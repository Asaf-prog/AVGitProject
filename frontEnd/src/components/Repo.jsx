export default function Repo({name}){
    return (
        <li>
            <h3>{!name ? 'null' : name}</h3>
         
        </li>
    );
}