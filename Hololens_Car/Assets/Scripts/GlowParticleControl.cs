using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GlowParticleControl : MonoBehaviour {
	private float timer;
	// Use this for initialization
	void Start () {
		timer = Time.time;
	}
	
	// Update is called once per frame
	void Update () {
		if (Time.time - timer > 3) {
			Destroy (this.gameObject);
		}
	}
}
