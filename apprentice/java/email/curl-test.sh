#!/bin/sh
curl -d '{"recipient": "llc@acm.org", "subject": "A wondrous test", "textBody": "This is a test through SendGrid."}' -X POST -H "Content-Type: application/json" http://localhost:8080/mail/send
