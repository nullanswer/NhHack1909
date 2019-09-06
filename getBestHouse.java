public class getBestHouse extends AsyncTask<String, Void, String> {
        http://localhost:3000/bestHouse
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                searchDateTextView.setText(stringFormat.format(selectedDate));
                Gson gson = new Gson();
                AttendanceByDateData attendanceByDateData = gson.fromJson(s, AttendanceByDateData.class);
                attendanceCountTextView.setText("입장 : " + attendanceByDateData.getEnterPeople() + " 명");
                completeCountTextView.setText("이수 : "+ attendanceByDateData.getCompletePeople() + " 명");
                exitCountTextView.setText("퇴장 : " + attendanceByDateData.getExitPeople() + " 명");
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("queryDate", strings[0])
                    .build();


            String temUrl = "bestHouse";
            Request request = new Request.Builder()
                    .header("x-access-token", SPController.getPreferences(.this, getResources().getString(R.string.parseValue)))
                    .url(NetworkClass.ServerAddress + temUrl)
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
        }


    }