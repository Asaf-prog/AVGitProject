import { useState } from 'react';
import Register from './Register.jsx';

export default function Login({onChange,name,setIsRegister,userPassword}) {
  
  const [userInput, setUserInput] = useState({
    userName: '',
    password: '',
  });

  const [showRegister, setShowRegister] = useState(false);

  const handleRegisterClick =  async () => {
    
    setShowRegister(true);
};

  const handleUsernameChange = (e) => {
    setUserInput({
      ...userInput,
      userName: e.target.value,
    });
  };

  const handlePasswordChange = (e) => {
    setUserInput({
      ...userInput,
      password: e.target.value,
    });
  };

  const  handleLogin = async () => {
        
    const url = "http://localhost:8080/AvGit/login";
      
    const userDTO = {
      userName: userInput.userName, 
      password: userInput.password
  };

  try{
   
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body:JSON.stringify(userDTO)
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const responseData = await response.json();
    console.log('Response from http://localhost:8080/AvGit/login', responseData);

   
    
    if (userInput.userName === responseData.userName && 
      userInput.password === responseData.password) {
      onChange();
      name.user = userInput.userName;
      userPassword.password = userInput.password;
      
    } else {
      // Handle login failure
      alert('Login failed. Please check your credentials.');
    }
  }catch(error){
    console.log('An error occurred: ', error.message);
  }
  };

  return (
    <span id="user-input">
      <h2>Login</h2>
      <div className="input-group">
       
      {! showRegister && (
       <>
       
       <label>User Name</label>
        <input
          type="text"
          placeholder="Username"
          value={userInput.userName}
          onChange={handleUsernameChange}
          className="input-field"
        />

        <label>Password</label>
        <input
          type="password"
          placeholder="Password"
          value={userInput.password}
          onChange={handlePasswordChange}
          className="input-field"
        />

       </>
      )}
        
      {! showRegister && (
        <button onClick={handleLogin} className="login-button">
          <h3>Login</h3>
        </button>
      )}
        {showRegister && (
        <div className="register-container">
          <h3>Register</h3>
          {<Register  
          setIsRegister={setIsRegister}
          name={name} 
          onChange={onChange} 
          userPassword={userPassword}/>}
        </div>
        )}
        {!showRegister && (
        <button onClick={handleRegisterClick} className="login-button">
          Register
        </button>
      )}
      </div>
    </span>
  );
}
