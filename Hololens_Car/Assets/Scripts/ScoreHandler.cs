using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScoreHandler : MonoBehaviour {
	public GameObject gameOverUI;
	private bool isGameOver;
	// Use this for initialization
	void Start () {
		isGameOver = false;
	}
	
	// Update is called once per frame
	void Update () {
		this.gameObject.transform.position = Camera.main.transform.position;
	}
	void OnCollisionEnter (Collision col) {
		Debug.Log ("hit");
		if (col.gameObject.tag == "Obstacle") {
			loseHealth ();
		}
	}

	void loseHealth() {
		if (!isGameOver) {
			gameOverUI.SetActive (true);
			isGameOver = true;
		}
	}
}


