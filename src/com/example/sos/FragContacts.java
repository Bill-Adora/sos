package com.example.sos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sos.data.DbSOS;
import com.example.sos.data.bean.Contact;

public class FragContacts extends Fragment {
	private EditText Name;
	private EditText phoneNumber;
	private EditText email;
	private EditText relation;
	private Button btnSave;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frag_contacts, container,
				false);
		bindViews(rootView);
		loadContacts();
		return rootView;
	}

	private void bindViews(View rootView) {
		Name = (EditText) rootView.findViewById(R.id.edittext1);
		phoneNumber = (EditText) rootView.findViewById(R.id.edittext2);
		email = (EditText) rootView.findViewById(R.id.edittext3);
		relation = (EditText) rootView.findViewById(R.id.edittext4);
		btnSave = (Button) rootView.findViewById(R.id.btnSave);

		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
	}

	private void loadContacts() {

		// get details to database
		DbSOS db = new DbSOS(getActivity());
		Contact contact = db.contact.get();
		db.close();

		if (contact == null)
			return;

		Name.setText(contact.getFullName());
		phoneNumber.setText(contact.getPhone());
		email.setText(contact.getEmail());
		relation.setText(contact.getRelation());

	}

	private void save() {
		Contact contact = new Contact();
		contact.setFullName(Name.getText().toString());
		contact.setPhone(phoneNumber.getText().toString());
		contact.setEmail(email.getText().toString());
		contact.setRelation(relation.getText().toString());

		// Set details to database
		DbSOS db = new DbSOS(getActivity());
		db.contact.set(contact);
		db.close();

		Toast.makeText(getActivity(), "Contact was saved successfully",
				Toast.LENGTH_SHORT).show();
	}
}
