package com.example.playful.tasklostchilds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.playful.tasklostchilds.Child.Child;
import com.example.playful.tasklostchilds.Child.ListChild;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdScreen extends Activity {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvdateOfBirth)
    TextView tvDateOfBirth;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvChildDescription)
    TextView tvChildDescription;
    @BindView(R.id.tvRegion)
    TextView tvRegion;
    @BindView(R.id.tvSituationDescription)
    TextView tvSituationDescription;
    @BindView(R.id.tvTimeOfLoss)
    TextView tvTimeOfLoss;
    @BindView(R.id.tvTimeOfRequest)
    TextView tvTimeOfRequest;

    private Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("childIndex")){
            child = ListChild.getInstance().getChild(getIntent().getIntExtra("childIndex", 0));
        }
        fillInfo();
    }

    private void fillInfo() {
        tvName.setText(child.getName());
        tvStatus.setText(child.getStatus().toString());
        tvGender.setText(child.getGender().toString());
        tvChildDescription.setText(child.getChildDescription());
        tvRegion.setText(child.getRegion());
        tvSituationDescription.setText(child.getSituationDescription());
        tvTimeOfLoss.setText(child.getTimeOfLoss());
        tvTimeOfRequest.setText(child.getTimeOfRequest());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        tvDateOfBirth.setText(simpleDateFormat.format(child.getDateOfBirth()));
    }

    @OnClick(R.id.btnComments)
    void onClick(){
        Intent intent = new Intent(ThirdScreen.this, FourthScreen.class);
        intent.putExtra("childID", child.getId());
        startActivity(intent);
    }
}
