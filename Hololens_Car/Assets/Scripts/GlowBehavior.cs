using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GlowBehavior : MonoBehaviour {
	public GameObject glowParticles;
	// Use this for initialization
	void Start () {
		StartCoroutine (createGlow (2));
	}
	
	// Update is called once per frame
	void Update () {

	}

	IEnumerator createGlow (float frequency) {
		for (;;) {
			Instantiate (glowParticles, this.transform.position, Quaternion.identity); 
			yield return new WaitForSeconds (1f / frequency);
		}
	}
}
