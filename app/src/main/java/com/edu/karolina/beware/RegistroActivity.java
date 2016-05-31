package com.edu.karolina.beware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.karolina.beware.model.UserVO;
import com.edu.karolina.beware.model.adapter.UserDbAdapter;

public class RegistroActivity extends AppCompatActivity {




    private final String TAG = RegistroActivity.class.getSimpleName();

        private UserDbAdapter userDbAdapter;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro);

            userDbAdapter = new UserDbAdapter(this);
        }

        private boolean validateForm(){
            boolean result = true;

            EditText etName = (EditText) findViewById(R.id.editText1);

            if(etName != null && !etName.getText().equals("") && etName.getText().length() > 2){
                result = true;
            }
            return result;
        }


        private UserVO getDataForm(){
            UserVO userVO = new UserVO();

            String name = ((EditText) findViewById(R.id.editText1)).getText().toString();

            String lastname = ((EditText) findViewById(R.id.editText2)).getText().toString();

            String nickname = ((EditText) findViewById(R.id.editText3)).getText().toString();

            String password = ((EditText) findViewById(R.id.editText4)).getText().toString();

            int age = Integer.parseInt(((EditText) findViewById(R.id.editText5)).getText().toString());

            String gender = ((EditText) findViewById(R.id.editText6)).getText().toString();

            int height = Integer.parseInt(((EditText) findViewById(R.id.editText7)).getText().toString());

            int weight = Integer.parseInt(((EditText) findViewById(R.id.editText8)).getText().toString());

            userVO.setName(name);
            userVO.setLastname(lastname);
            userVO.setNickname(nickname);
            userVO.setPassword(password);
            userVO.setAge(age);
            userVO.setGender(gender);
            userVO.setHeight(height);
            userVO.setWeight(weight);
            return userVO;
        }



        public void registerClicked(View v){
            if(validateForm()){
                userDbAdapter.open();
                if(userDbAdapter.insertUser(getDataForm()) != 0){
                    Log.i(TAG, "Caso exitoso");
                    Intent nextActivity = new Intent(this, InicioSeccionActivity.class);
                    startActivity(nextActivity);
                    finish();
                } else {

                    Log.i("LOGIN", "Error de insertar");
                    Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
                }
            } else {
                String msg = getString(R.string.error_form);
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            }
        }


        @Override
        public void onBackPressed() {
            // TODO Auto-generated method stub
            super.onBackPressed();
            Intent nextActivity = new Intent(this, InicioSeccionActivity.class);
            startActivity(nextActivity);
            finish();
        }





  }