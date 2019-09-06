package com.example.nhhack1909;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhhack1909.Data.BestHouseData;

public class SRViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView landPrice, landLoc, landSubs, landType;
    ImageView srBackground;
    Context context;
    BestHouseData tempData;

    public SRViewHolder(@NonNull final View itemView) {

        super(itemView);
        context = itemView.getContext();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailInfoActivity.class);
                intent.putExtra("id",tempData.getId());
                context.startActivity(intent);
            }
        });
        landPrice = itemView.findViewById(R.id.landPrice);
        landLoc = itemView.findViewById(R.id.landLoc);
        landSubs = itemView.findViewById(R.id.landSubs);
        landType = itemView.findViewById(R.id.landType);

        srBackground = itemView.findViewById(R.id.srBackground);
    }

    public void setData(BestHouseData data) {
        this.tempData = data;
        landPrice.setText(data.getLand_pay());
        landSubs.setText(data.getLand_description());
        landLoc.setText(data.getLand_address());
        landType.setText(data.getLand_type());

        Glide.with(context).load(data.getImg()).into(srBackground);


//        id":422,"land_type":"1","land_pay":810000,"land_size":"3273","land_jimk":"답","land_admin":"강진지사","land_phone":"061-430-7731","land_description":"방울방울 터지는 250평","land_lat":null,"land_lng":null,"land_address":"전라남도 강진군 성전면 월하리 1421-7"},{"id":421,"land_type":"1","land_pay":1300000,"land_size":"1551\r","land_jimk":"답","land_admin":"강진지사","land_phone":"061-430-7731","land_description":"고구마농사하기 좋은 100평","land_lat":null,"land_lng":null,"land_address":"전라남도 강진군 강진읍 서산리 61-2"},{"id":420,"land_type":"1","land_pay":722150,"land_size":"5555\r","land_jimk":"답","land_admin":"해남.완도지사","land_phone":"061-530-1515","land_description":"내고장 7월은...","land_lat":null,"land_lng":null,"land_address":"전라남도 해남군 해남읍 복평리 1217-4"}]
    }

    @Override
    public void onClick(View view) {

    }
}
