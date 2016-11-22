package com.example.mingh.medappjam;

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Date;

import cz.msebera.android.httpclient.entity.mime.Header;


public class InChatActivity extends AppCompatActivity implements View.OnClickListener {
    EditText messageInput;
    Button sendButton;
    Button emojiButton;
    Button audioButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get our input field by its ID
        messageInput = (EditText) findViewById(R.id.message_input);

        // get our button by its ID
        sendButton = (Button) findViewById(R.id.send_button);
        emojiButton = (Button) findViewById(R.id.emoji_button);
        audioButton = (Button) findViewById(R.id.audio_button);
        backButton = (Button) findViewById(R.id.back_button);

        // set its click listener
        sendButton.setOnClickListener(this);
        emojiButton.setOnClickListener(this);
        audioButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        postMessage();
    }

    private void postMessage() {
//        String text = messageInput.getText().toString();
//
//        // return if the text is blank
//        if(text.equals("")) { return; }
//
//        RequestParams params = new RequestParams();
//
//        // set our JSON object
//        params.put("text", text);
////        params.put("name", username);
//        params.put("time", new Date().getTime());
//
//        // create our HTTP client
//        AsyncHttpClient client = new AsyncHttpClient();
//
//        client.post(, params, new JsonHttpResponseHandler(){
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        messageInput.setText("");
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        "Something went wrong :(",
//                        Toast.LENGTH_LONG
//                ).show();
//            }
//        });

    }
}



