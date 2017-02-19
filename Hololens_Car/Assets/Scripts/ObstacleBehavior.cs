using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObstacleBehavior : MonoBehaviour {
	private float timer;
	// Use this for initialization
	void Start () {
		timer = Time.time;
		gameObject.transform.Translate(0, 0, -1);
		gameObject.transform.Rotate (0, 90, 0);
		gameObject.tag = "Obstacle";
	}
	
	// Update is called once per frame
	void Update () {
		gameObject.transform.Translate (new Vector3 (0, 0, (Mathf.Cos (2*(Time.time - timer))/100)));
		if (Time.time - timer > 5) {
			Destroy (this.gameObject);
		}
	}
}
