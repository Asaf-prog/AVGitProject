import { useState } from 'react';

export default function Login({onChange,name}) {
  const [userInput, setUserInput] = useState({
    userName: '',
    password: '',
  });

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

  const handleLogin = () => {
    console.log('User Name:', userInput.userName);
    console.log('Password:', userInput.password);


    if (userInput.userName === 'asaf' && userInput.password === '1212') {
        onChange();
        name.user = userInput.userName;
        //send request
      } else {
        // Handle login failure
        alert('Login failed. Please check your credentials.');
      }
  };

  return (
    <span id="user-input">
      <h2>Login</h2>
      <div className="input-group">
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
        <button onClick={handleLogin} className="login-button">
          Login
        </button>
      </div>
    </span>
  );
}
