using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObstacleBehavior : MonoBehaviour {
	private float timer;
	// Use this for initialization
	void Start () {
		timer = Time.time;
	}
	
	// Update is called once per frame
	void Update () {
		gameObject.transform.Translate (new Vector3 (Mathf.Cos (Time.time - timer)/100, 0, 0));
		if (Time.time - timer > 5) {
			Destroy (this.gameObject);
		}
	}
}
